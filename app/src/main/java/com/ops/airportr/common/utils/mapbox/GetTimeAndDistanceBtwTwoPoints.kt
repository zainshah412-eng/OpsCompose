package com.ops.airportr.common.utils.mapbox

import android.content.Context
import android.util.Log
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.geojson.Point
import com.mapbox.navigation.base.extensions.applyDefaultNavigationOptions
import com.mapbox.navigation.base.extensions.applyLanguageAndVoiceUnitOptions
import com.mapbox.navigation.base.extensions.coordinates
import com.mapbox.navigation.base.options.NavigationOptions
import com.mapbox.navigation.base.route.NavigationRoute
import com.mapbox.navigation.base.route.NavigationRouterCallback
import com.mapbox.navigation.base.route.RouterFailure
import com.mapbox.navigation.base.route.RouterOrigin
import com.mapbox.navigation.base.route.toDirectionsRoutes
import com.mapbox.navigation.core.MapboxNavigation
import com.mapbox.navigation.core.MapboxNavigationProvider

class GetTimeAndDistanceBtwTwoPoints(private var context: Context?) {
    private var mapboxNavigation: MapboxNavigation? = null
    private var onDistanceAndDurationListener: OnGetDistanceAndDurationListener? = null

    fun setOnGetDistanceAndDurationListener(onGetDistanceAndDurationListener: OnGetDistanceAndDurationListener?) {
        this.onDistanceAndDurationListener = onGetDistanceAndDurationListener
    }

    init {
        mapBoxInitialize()
    }

    private fun mapBoxInitialize() {
        context?.let { ctx ->
            val navigationOptions = NavigationOptions.Builder(ctx)
                .accessToken(constants.mapboxApiKey)
                .build()
            mapboxNavigation = MapboxNavigationProvider.create(navigationOptions)
//            mapboxNavigation = if (MapboxNavigationProvider.isCreated()) {
//                MapboxNavigationProvider.retrieve()
//            } else {
//                val navigationOptions = NavigationOptions.Builder(ctx)
//                    .accessToken(constants.mapboxApiKey)
//                    .build()
//                MapboxNavigationProvider.create(navigationOptions)
//            }
        }
    }

    fun getDirectionalRoute(
        originPoint: Point,
        destinationPoint: Point
    ) {
        context?.let { ctx ->
            try {
//                mapBoxInitialize()
                onDistanceAndDurationListener?.onLoading(true)
                val routeOptions = RouteOptions.builder()
                    .applyDefaultNavigationOptions()
                    .applyLanguageAndVoiceUnitOptions(ctx)
                    .coordinates(originPoint, null, destinationPoint)
                    .layersList(listOf(mapboxNavigation?.getZLevel(), null))
                    .build()

//                Log.wtf("getDirectionalRoute", "originPoint: $originPoint")
//                Log.wtf("getDirectionalRoute", "destinationPoint: $destinationPoint")
//                Log.wtf("getDirectionalRoute", "getZLevel: $${mapboxNavigation?.getZLevel()}")

                mapboxNavigation?.requestRoutes(
                    routeOptions,
                    object : NavigationRouterCallback {
                        override fun onCanceled(
                            routeOptions: RouteOptions,
                            routerOrigin: RouterOrigin
                        ) {
                            Log.wtf("getDirectionalRoute", "onCanceled")
                            gotError("onCanceled")
                        }

                        override fun onFailure(
                            reasons: List<RouterFailure>,
                            routeOptions: RouteOptions
                        ) {
                            Log.wtf("getDirectionalRoute", "onFailure")
                            gotError("onFailure")
                        }

                        override fun onRoutesReady(
                            routes: List<NavigationRoute>,
                            routerOrigin: RouterOrigin
                        ) {
                            Log.wtf(
                                "getDirectionalRoute",
                                "Route onRoutesReady"
                            ) // Change "YourTag" to an appropriate tag for logging
                            if (routes.isNullOrEmpty()) {
                                Log.wtf(
                                    "getDirectionalRoute",
                                    "Route onRoutesReady are empty"
                                ) // Change "YourTag" to an appropriate tag for logging
                                gotError("")
                                return
                            }
                            var distance = 0.0
                            var time = 0.0
                            routes.toDirectionsRoutes().forEach {
                                distance += it.distance()
                                time += it.duration()
                            }
                            Log.wtf(
                                "getDirectionalRoute",
                                "Route onRoutesReady distance:$distance / time: $time"
                            ) // Change "YourTag" to an appropriate tag for logging

                            onDistanceAndDurationListener?.onLoading(false)
                            onDistanceAndDurationListener?.onSuccess(distance, time)
                        }
                    }
                )
            } catch (e: Exception) {
                gotError(e.message ?: "")
            }
        }
    }

    private fun gotError(error: String) {
        onDistanceAndDurationListener?.onLoading(false)
        onDistanceAndDurationListener?.onFailed(error)
    }

    interface OnGetDistanceAndDurationListener {
        fun onSuccess(distance: Double, duration: Double)
        fun onFailed(message: String)
        fun onLoading(isLoading: Boolean)
    }

    fun onDestroy() {
        context = null
        mapboxNavigation = null
        onDistanceAndDurationListener = null
        MapboxNavigationProvider.destroy()
    }
}