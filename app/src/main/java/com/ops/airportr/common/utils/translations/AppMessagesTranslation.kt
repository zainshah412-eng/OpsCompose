package com.ops.airportr.common.utils.translations

import android.content.Context
import com.ops.airportr.R

class AppMessagesTranslation(val context: Context) {

    fun translateErrorMessagesString(message: String): String {
        with(message.toLowerCase()) {
            when {
                equals(convertToLowercase("User does not have mobile number stored.")) -> return context.getString(
                    R.string.user_has_no_mobile_number_stored
                )

                equals(convertToLowercase("Your account is now locked. Please reset your password or try again later")) -> return context.getString(
                    R.string.your_account_is_now_locked_please_reset_your_password
                )

                equals(convertToLowercase("SMS could not be sent. The user phone number is not valid. Please verify with your supervisor.")) -> return context.getString(
                    R.string.sms_could_not_be_send
                )

                contains(convertToLowercase("Your one time code is incorrect. You have")) -> return context.getString(
                    R.string.your_one_time_code_is_incorrect,
                    getAttemptNumber(message)
                )

                contains(convertToLowercase("Sorry, your code is only valid for")) -> return context.getString(
                    R.string.sorry_your_code_is_only_valid_for,
                    getRemainingTime(message)
                )

                equals(convertToLowercase("WE CANNOT CHECK-IN YOUR BAGS")) -> return context.getString(
                    R.string.we_can_not_check_in_your_bag
                )

                equals(convertToLowercase("I'm sorry, we cannot check in firearms")) -> return context.getString(
                    R.string.cannot_check_in_firearms
                )

                equals(convertToLowercase("I'm sorry, we cannot check in bags that were interfered by anyone but you.")) -> return context.getString(
                    R.string.cannot_check_in_bags_that_were_interfered
                )

                equals(convertToLowercase("I'm sorry, we cannot check in bags that you're carrying for someone else.")) -> return context.getString(
                    R.string.cannot_check_in_bags_that_you_carrying
                )

                equals(convertToLowercase("Bags")) -> return context.getString(R.string.ccd_bags)
                equals(convertToLowercase("Bag")) -> return context.getString(R.string.ccd_bag)

                contains(convertToLowercase("If you have extra, oversized or overweight bags, you may be charged in line with the airline’s policy.\n\nInternational flights may be subject to higher charges.")) -> return context.getString(
                    R.string.self_service_british_airline_excess_charges,
                    getAirlineText(message)
                )

                contains(convertToLowercase("Multiple ExcessBaggage payments cannot be processed")) -> return context.getString(
                    R.string.multiple_excess_baggage,
                    message.substringAfter(convertToLowercase("processed for the booking"))
                )


                contains(convertToLowercase("I understand")) -> return context.getString(R.string.i_understand)
                contains(convertToLowercase("Remember to check-in online")) -> return context.getString(
                    R.string.remember_to_check_in_online
                )

                contains(convertToLowercase("We can’t put your bags on the plane unless you have checked-in online with British Airways.")) -> return context.getString(
                    R.string.we_cannot_put_your_bags_on_the_plane_unless
                )

                contains(convertToLowercase("We can’t put your bags on the plane unless you have checked-in online with Swiss.")) -> return context.getString(
                    R.string.we_cannot_put_your_bags_on_the_plane_unless_swiss
                )

                contains(convertToLowercase("We don\\'t know when the customer is expecting you")) -> return context.getString(
                    R.string.we_do_not_know_when_the_customer_is_expecting_you
                )

                contains(convertToLowercase("Ring the bell")) -> return context.getString(
                    R.string.ring_the_bell
                )

                contains(convertToLowercase("Wait for the customer to approve")) -> return context.getString(
                    R.string.approve
                )

                contains(convertToLowercase("The charges are set by and at the discretion of Air France.")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_air_france
                )

                contains(convertToLowercase("The charges are set by and at the discretion of KLM.")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_klm_airline
                )


                contains(convertToLowercase("The charges are <b>set by and at the discretion of easyJet</b>.")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_easyjet_airline,
                    message.substringAfter(convertToLowercase("Overweight bags are <b>"))
                        .substringBefore(convertToLowercase("per kg")),
                    message.substringAfter(convertToLowercase("Extra bags are <b>"))
                        .substringBefore(convertToLowercase("</b> each."))
                )

                contains(convertToLowercase("The charges are <b>set by and at the discretion of American Airlines</b>.")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_american_airways,
                    message.substringAfter(convertToLowercase("of "))
                        .substringBefore(convertToLowercase("</b>.")),
                    message.substringAfter(convertToLowercase("Overweight bags are <b>"))
                        .substringBefore(convertToLowercase(" each</b>.")),
                    message.substringAfter(convertToLowercase("Extra bags are <b>"))
                        .substringBefore(convertToLowercase(" each</b> depending"))
                )

                contains(convertToLowercase("The charges are <b>set by and at the discretion of Austrian Airlines</b>")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_austrain_airways,
                    message.substringAfter(convertToLowercase("of "))
                        .substringBefore(convertToLowercase("</b>."))
                )

                contains(convertToLowercase("The charges are <b>set by and at the discretion of Lufthansa</b>")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_lufthansa_airways,
                    message.substringAfter(convertToLowercase("of "))
                        .substringBefore(convertToLowercase("</b>."))
                )

                contains(convertToLowercase("The charges are <b>set by and at the discretion of British Airways</b>")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_american_airways,
                    message.substringAfter("of ").substringBefore("</b>."),
                    message.substringAfter("Overweight bags are <b>").substringBefore(" each</b>"),
                    message.substringAfter("Extra bags are <b>")
                        .substringBefore("</b> each depending on your cabin")
                )

                contains(convertToLowercase("The charges are <b>set by and at the discretion of Singapore Airlines</b>.")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_singapore_airline
                )

                contains(convertToLowercase("<b>Additional charges</b>\nBaggage fees are set by Emirates.\n\nIf you have extra, oversized or overweight bags, you may be charged in line with the airline’s policy.\n\nInternational flights may be subject to higher charges.")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_emirates_airline
                )

                contains(convertToLowercase("The charges are <b>set by and at the discretion of easyJet</b>.")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_easyjet_airline,
                    message.substringAfter(convertToLowercase("Overweight bags are <b>"))
                        .substringBefore(convertToLowercase("per kg")),
                    message.substringAfter(convertToLowercase("Extra bags are <b>"))
                        .substringBefore(convertToLowercase("</b> each."))
                )

                contains(convertToLowercase("The charges are <b>set by and at the discretion of")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_general_msg,
                    message.substringAfter(convertToLowercase("of "))
                        .substringBefore(convertToLowercase("</b>.")),
                    message.substringAfter(convertToLowercase("Overweight bags are <b>"))
                        .substringBefore(convertToLowercase(" each</b>")),
                    message.substringAfter(convertToLowercase("Extra bags are <b>"))
                        .substringBefore(convertToLowercase("</b> each depending on your cabin"))
                )


                contains(convertToLowercase("The charges are set by and at the discretion of ")) -> return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_swiss_airways,
                    message.substringAfter(convertToLowercase("of "))
                        .substringBefore(convertToLowercase(".")),
                    message.substringAfter(convertToLowercase("Overweight bags are "))
                        .substringBefore(convertToLowercase(" each in excess")),
                    message.substringAfter(convertToLowercase("Extra bags are "))
                        .substringBefore(convertToLowercase(" each")),
                    message.substringAfter(convertToLowercase("to "))
                        .substringBefore(convertToLowercase(" per"))
                )

                contains(convertToLowercase("Airport")) -> return context.getString(
                    R.string.air_port
                )

                contains(convertToLowercase("Internet Connection Error")) -> return context.getString(
                    R.string.internet_connection_error
                )

                contains(convertToLowercase("User Not Found for Id:")) -> return context.getString(
                    R.string.user_not_found_for_id,
                    message.substringAfter(convertToLowercase("Id:"))
                )

                contains(convertToLowercase("User Not Found")) -> return context.getString(R.string.user_not_fount)
                contains(convertToLowercase("Your reset password link is only valid for ")) -> return context.getString(
                    R.string.your_password_link_is_only_valid_for,
                    message.substringAfter(convertToLowercase("for"))
                        .substringBefore(convertToLowercase(" hours"))
                )

                contains(convertToLowercase("Your reset password link has expired. Please generate a new link.")) -> return context.getString(
                    R.string.your_reset_password_link_has_been_expired
                )

                contains(convertToLowercase("There was an error with your reset password link. Please generate a new link.")) -> return context.getString(
                    R.string.there_was_an_error_with_your_reset_password_link
                )

                contains(convertToLowercase("Sorry, your code was incorrect. Please try again or generate a new one.")) -> return context.getString(
                    R.string.sorry_your_code_was_incorrect
                )

                contains(convertToLowercase("There was an error with your booking Url link. Please generate a new link.")) -> return context.getString(
                    R.string.there_was_an_error_with_your_booking_url_link
                )

                contains(convertToLowercase("Sorry, your code has already been used. Please generate a new one to access your account")) -> return context.getString(
                    R.string.sorry_your_code_has_already_been_used
                )

                contains(convertToLowercase("Your login details are incorrect. Please try again or reset your password.")) -> return context.getString(
                    R.string.your_login_details_are_incorrect
                )

                contains(convertToLowercase("Sorry,")) -> return context.getString(
                    R.string.sorry_email_address_is_already_registered_user,
                    message.substringAfter(convertToLowercase(","))
                        .substringBefore(convertToLowercase(" is"))
                )

                contains(convertToLowercase("Password Incorrect for User ")) -> return context.getString(
                    R.string.password_incorrect_for_user,
                    message.substringAfter(convertToLowercase("User"))
                )

                contains(convertToLowercase("Unable to display booking.")) -> return context.getString(
                    R.string.unable_to_display_booking
                )

                contains(convertToLowercase("User does not have the required role permissions to view boarding pass photos.")) -> return context.getString(
                    R.string.user_does_not_have_the_require_role_permission
                )

                contains(convertToLowercase("User does not have the required role permissions to view photo ID images.")) -> return context.getString(
                    R.string.user_does_not_have_the_required_role_permission_to_view_photo_id_images
                )

                contains(convertToLowercase("User does not have the required role permissions to view passport photos.")) -> return context.getString(
                    R.string.user_does_not_have_the_required_role_permission_to_view_passport_photos
                )

                contains(convertToLowercase("Image Not Found for Id")) -> return context.getString(
                    R.string.image_not_fount_for_id, message.substringAfter(convertToLowercase(":"))
                )

                contains(convertToLowercase("All values in the booking summary request are empty. Please provide a value.")) -> return context.getString(
                    R.string.all_values_in_the_booking_summary_request_are_empty
                )

                contains(convertToLowercase("Number of bags cannot be 0.")) -> return context.getString(
                    R.string.number_of_bags_can_not_be_0
                )

                contains(convertToLowercase("No Actions found for Booking Reference")) -> return context.getString(
                    R.string.no_action_found_for_booking_reference,
                    message.substringAfter(convertToLowercase(":"))
                        .substringBefore(convertToLowercase(" and")),
                    message.substringAfter(convertToLowercase("Reference"))
                )

                contains(convertToLowercase("Cannot determine the Live Location of ")) -> return context.getString(
                    R.string.can_not_determine_the_live_location_of_luggage,
                    message.substringAfter(convertToLowercase("Luggage"))
                )

                contains(convertToLowercase("This passenger must visit the BA Check-In desks with their bag before proceeding through Security.")) -> return context.getString(
                    R.string.this_passenger_must_visit_the_ba_check_in_desks_with
                )

                contains(convertToLowercase("There has been an error.")) -> return context.getString(
                    R.string.there_has_been_an_error
                )

                contains(convertToLowercase("Booking Reference Supplied Not Found")) -> return context.getString(
                    R.string.booking_reference_supplied_not_found
                )

                contains(convertToLowercase("Booking not found for booking journey reference:")) -> return context.getString(
                    R.string.booking_not_found_for_booking_journey_reference_is_this_pnr,
                    message.substringAfter(convertToLowercase("reference:"))
                        .substringBefore(convertToLowercase(" Is"))
                )

                contains(convertToLowercase("Error, could not upload image")) -> return context.getString(
                    R.string.error_could_not_upload_image,
                    message.substringAfter(convertToLowercase("image:"))
                )

                contains(convertToLowercase("API already exists under the name")) -> return context.getString(
                    R.string.api_already_exists_under_the_name,
                    message.substringAfter(convertToLowercase("name:"))
                )

                contains(convertToLowercase("Passenger and other details not saved")) -> return context.getString(
                    R.string.api_already_exists_under_the_name
                )

                contains(convertToLowercase("You have provided ")) -> return context.getString(
                    R.string.you_have_provided
                )

                contains(convertToLowercase("Passenger Not Found.")) -> return context.getString(
                    R.string.passenger_not_found
                )

                contains(convertToLowercase("Expected Object")) -> return context.getString(
                    R.string.expect_object,
                    message.substringAfter(convertToLowercase("Object:"))
                        .substringBefore(convertToLowercase(" is"))
                )

                contains(convertToLowercase("Passenger Id")) -> return context.getString(
                    R.string.passenger_id,
                    message.substringAfter(convertToLowercase("Id:"))
                        .substringBefore(convertToLowercase(" not"))
                )

                contains(convertToLowercase("Job Number")) -> return context.getString(
                    R.string.job_number_not_found,
                    message.substringAfter(convertToLowercase("Number"))
                        .substringBefore(convertToLowercase(" not"))
                )

                contains(convertToLowercase("No Passenger Luggage entries found.")) -> return context.getString(
                    R.string.no_passenger_luggage_entries_found
                )

                contains(convertToLowercase("Journey Linked Passenger not found.")) -> return context.getString(
                    R.string.journey_linked_passenger_not_found
                )

                contains(convertToLowercase("Params objects are incorrect. An object is either null, empty or default.")) -> return context.getString(
                    R.string.params_objects_are_incorrect
                )

                contains(convertToLowercase("Cannot parse string with a value of ")) -> return context.getString(
                    R.string.can_not_parse_string_with,
                    message.substringAfter(convertToLowercase("of"))
                        .substringBefore(convertToLowercase(" to")),
                    message.substringAfter(convertToLowercase("type:"))
                )

                contains(convertToLowercase("Job not found for Id:")) -> return context.getString(
                    R.string.job_not_found_for_id,
                    message.substringAfter(convertToLowercase("Id:"))
                )

                contains(convertToLowercase("Sorry the location you specified could not be found.")) -> return context.getString(
                    R.string.sorry_the_location_you_specified_could_not_be_found
                )

                contains(convertToLowercase("UserId:")) -> return context.getString(
                    R.string.user_id_does_not_have_any_permission_to_update_job_activity_status,
                    message.substringAfter(convertToLowercase("UserId:"))
                        .substringBefore(convertToLowercase(" does"))
                )

                contains(convertToLowercase("Action:")) -> return context.getString(
                    R.string.action_already_completed,
                    message.substringAfter(convertToLowercase("Action:"))
                        .substringBefore(convertToLowercase(" already"))
                )

                contains(convertToLowercase("Passenger ")) -> return context.getString(
                    R.string.passenger_has_no_bags,
                    message.substringAfter(convertToLowercase("Passenger "))
                        .substringBefore(convertToLowercase(" has"))
                )

                contains(convertToLowercase("No bags available in storage booking(s) ")) -> return context.getString(
                    R.string.no_bags_available_in_storage_bookings
                )

                contains(convertToLowercase("Passenger Luggage already inactive for Id:")) -> return context.getString(
                    R.string.passenger_luggage_already_inactive_for_id,
                    message.substringAfter(convertToLowercase("Id:"))
                )

                contains(convertToLowercase("BookingReference: ")) -> return context.getString(
                    R.string.booking_reference_and_booking_journey_provided_do_not_match_to_an_existing_booking,
                    message.substringAfter(convertToLowercase("BookingReference:"))
                        .substringBefore(convertToLowercase(" and")),
                    message.substringAfter(convertToLowercase("BookingJourney:"))
                        .substringBefore(convertToLowercase(" provided"))
                )

                contains(convertToLowercase("of type ")) -> return context.getString(
                    R.string.instance_name_of_type_instance_type_is_null,
                    message.substringBefore(convertToLowercase(" of"))
                        .substringBefore(convertToLowercase(" type")),
                    message.substringAfter(convertToLowercase("type"))
                        .substringBefore(convertToLowercase(" is"))
                )

                contains(convertToLowercase("Passenger Luggage Not Found for Id: ")) -> return context.getString(
                    R.string.passenger_luggage_not_found_for_id,
                    message.substringAfter(convertToLowercase("Id:"))
                )

                contains(convertToLowercase("Image cannot be viewed because this is not a luggage/driver image.")) -> return context.getString(
                    R.string.image_can_not_be_viewed_because_this_is_not_a_luggage_image
                )

                contains(convertToLowercase("is null, empty or invalid. Please fill the ")) -> return context.getString(
                    R.string.request_type_is_null_empty_or_invalid,
                    message.substringBefore(convertToLowercase(" is")),
                    message.substringBefore(convertToLowercase(" is"))
                )

                contains(convertToLowercase("Journey Linked Passenger Not Found for id: ")) -> return context.getString(
                    R.string.journey_linked_passenger_not_found_for_id,
                    message.substringAfter(convertToLowercase("id:"))
                        .substringBefore(convertToLowercase(" as")),
                )

                contains(convertToLowercase("Email address entered: ")) -> return context.getString(
                    R.string.enmail_address_entered_does_not_exist,
                    message.substringAfter(convertToLowercase("entered:"))
                        .substringBefore(convertToLowercase(" does")),
                )

                contains(convertToLowercase("Booking not found for reference:")) -> return context.getString(
                    R.string.booking_not_found_for_reference,
                    message.substringAfter(convertToLowercase("reference: "))
                )

                contains(convertToLowercase("job_entries_not_found")) -> return context.getString(
                    R.string.job_entries_not_found
                )

                contains(convertToLowercase("Unable To Downgrade Luggage with PassengerLuggageId: ")) -> return context.getString(
                    R.string.unable_to_downgrade_luggage_with_passenger_luggage_id,
                    message.substringAfter(convertToLowercase("PassengerLuggageId:"))
                )

                contains(convertToLowercase("Luggage with Id: ")) -> return context.getString(
                    R.string.luggage_with_id_has_already_been_accepted,
                    message.substringAfter(convertToLowercase("Id: "))
                        .substringBefore(convertToLowercase(" has"))
                )

                contains(convertToLowercase("Error assigning luggage Id: ")) -> return context.getString(
                    R.string.error_assigning_luggage_id,
                    message.substringAfter(convertToLowercase("Id: "))
                        .substringBefore(convertToLowercase(" to")),
                    message.substringAfter(convertToLowercase("userId: "))
                        .substringBefore(convertToLowercase(" for")),
                    message.substringAfter(convertToLowercase("jobId: "))
                )

                contains(convertToLowercase("Print tag failed because enough information not available.")) -> return context.getString(
                    R.string.print_tag_failed_because_enough_information_not_available
                )

                contains(convertToLowercase("Print tag failed because ")) -> return context.getString(
                    R.string.print_tag_failed_error,
                    message.substringAfter(convertToLowercase("because "))
                )

                contains(convertToLowercase("Excess bag processing disabled")) -> return context.getString(
                    R.string.excess_bag_processing_disabled
                )

                contains(convertToLowercase("Unable to print the tag ")) -> return context.getString(
                    R.string.unable_to_print_the_tag,
                    message.substringAfter(convertToLowercase("tag "))
                        .substringBefore(convertToLowercase(" because"))
                )

                contains(convertToLowercase("Tag already printed")) -> return context.getString(
                    R.string.tag_already_printed
                )

                contains(convertToLowercase("Passenger not checked in")) -> return context.getString(
                    R.string.passenger_not_checked_in
                )

                contains(convertToLowercase("Itinerary does not match AirPortr records. Try manual mode")) -> return context.getString(
                    R.string.item_array_does_not_match
                )

                contains(convertToLowercase("Pax boarding pass(or equivalent) not found")) -> return context.getString(
                    R.string.pax_boarding_pass_or_equivalent_not_found
                )

                contains(convertToLowercase("Unable to print the tag as the seal code is empty.")) -> return context.getString(
                    R.string.unable_to_print_the_tag_as_the_seal_code_is_empty
                )

                contains(convertToLowercase("We don't know when the customer is expecting you")) -> return context.getString(
                    R.string.we_do_not_know_when_the_customer_is_expecting_you
                )

                contains(convertToLowercase("Customer Services has approved arrival")) -> return context.getString(
                    R.string.customer_services_has_been_approved_aarival
                )

                contains(convertToLowercase("Early-acceptance SMS has been sent")) -> return context.getString(
                    R.string.early_acceptance_sms_has_been_sent
                )

                contains(convertToLowercase("You arrived just before the slot")) -> return context.getString(
                    R.string.you_arrived_just_before_the_slot
                )

                contains(convertToLowercase("You're more than 40 minutes early")) -> return context.getString(
                    R.string.you_are_more_than_40_minutes_early
                )

                contains(convertToLowercase("Onfleet job is not started")) -> return context.getString(
                    R.string.on_fleet_job_is_not_started
                )

                contains(convertToLowercase("An SMS has been sent to the customer asking if ")) -> return context.getString(
                    R.string.an_sms_has_been_sent_to_the_customer_asking_if,
                    message.substringAfter(convertToLowercase("if "))
                        .substringBefore(convertToLowercase(" they")),
                    message.substringAfter(convertToLowercase("early. "))
                        .substringBefore(convertToLowercase(" Wait"))
                )

                contains(convertToLowercase("At ")) -> return context.getString(
                    R.string.at_time_the_customer_will_be_sent_an_sms,
                    message.substringAfter(convertToLowercase("At "))
                        .substringBefore(convertToLowercase(" the")),
                    message.substringAfter(convertToLowercase("be "))
                        .substringBefore(convertToLowercase(" sent"))
                )

                contains(convertToLowercase("You've arrived early, so 40 minutes before the ")) -> return context.getString(
                    R.string.you_have_arrived_early,
                    message.substringAfter(convertToLowercase("the "))
                )

                contains(convertToLowercase("if they want you to come in early ")) -> return context.getString(
                    R.string.if_they_want_you_to_come_in_early,
                    message.substringAfter(convertToLowercase("early "))
                        .substringBefore(convertToLowercase(" Wait"))
                )

                contains(convertToLowercase("This job is not due to be started yet")) -> return context.getString(
                    R.string.this_job_is_not_due_to_be_started_if_you_have_new
                )

                contains(convertToLowercase("Multiple ")) -> return context.getString(
                    R.string.multiple_payments_can_not_be_processed_for_the_booking,
                    message.substringAfter(convertToLowercase("Multiple "))
                        .substringBefore(convertToLowercase(" payments")),
                    message.substringAfter(convertToLowercase("booking "))
                )

                contains(convertToLowercase("No card details found for booking")) -> return context.getString(
                    R.string.no_card_details_found_for_booking,
                    message.substringAfter(convertToLowercase("booking "))
                )

                contains(convertToLowercase("Contact details not found")) -> return context.getString(
                    R.string.contact_details_not_found
                )

                contains(convertToLowercase("Unable to retrieve noBookingStorage entries for booking")) -> return context.getString(
                    R.string.unable_to_retrieve_no_booking_storage_entries_for_booking
                )

                contains(convertToLowercase("booking journey entry not found")) -> return context.getString(
                    R.string.booking_journey_entry_not_found
                )

                contains(convertToLowercase("onfleet job not created since booking has bags i.e. onfleet task/acceptance has started")) -> return context.getString(
                    R.string.on_fleet_job_not_created_since
                )

                contains(convertToLowercase("Logistic Job not deleted, since booking has no existing logistic job entry")) -> return context.getString(
                    R.string.logistic_job_not_deleted_logistic_job_entry
                )

                contains(convertToLowercase("Logistic Job not deleted, since booking has no logisticId")) -> return context.getString(
                    R.string.logistic_job_not_deleted_logistic_id
                )

                contains(convertToLowercase("Logistic Job has already been cancelled")) -> return context.getString(
                    R.string.logistic_job_has_already_been_cancelled
                )

                contains(convertToLowercase("Payment taken for excess baggage")) -> return context.getString(
                    R.string.payment_taken_for_excess_baggags
                )


                contains(convertToLowercase("Booking not found for reference:")) -> {
                    return context.getString(
                        R.string.booking_not_found_for_booking_journey_reference,
                        message.substringAfter(convertToLowercase(":"))
                    )
                }

                equals(convertToLowercase("No Internet Connection")) -> return context.getString(R.string.no_internet_connection)
                contains(convertToLowercase("It looks like system is down, support tech team has been notified.")) -> return context.getString(
                    R.string.it_looks_like_system_is_down
                )


                contains(convertToLowercase("Do you have any of the above in your luggage?")) -> return context.getString(
                    R.string.do_you_have_any_of_the_aboive_n_your_luggage
                )

                contains(convertToLowercase("Have you been given any items to carry for someone else?")) -> return context.getString(
                    R.string.have_you_been_given_any_item
                )

                contains(convertToLowercase("Could anyone have interfered with your luggage?")) -> return context.getString(
                    R.string.could_anyone_have_interfered_with
                )

                contains(convertToLowercase("Are you carrying any firearms?")) -> return context.getString(
                    R.string.are_you_carrying_any_firearm
                )

                contains(convertToLowercase("I agree")) -> return context.getString(R.string.i_agree)
                contains(convertToLowercase("You're all set!")) -> return context.getString(R.string.you_are_all_set)
                contains(convertToLowercase("Please hand over the phone back to our staff")) -> return context.getString(
                    R.string.please_hand_th_phone_back_to_our_staff
                )

                contains(convertToLowercase("Start the Onfleet job")) -> return context.getString(R.string.start_the_onfleet_job)
                contains(convertToLowercase("Customer is expecting you at")) -> return context.getString(
                    R.string.customer_is_expecting_you_at,
                    message.substringAfter(convertToLowercase("at"))
                )

                contains(convertToLowercase("This job is not due to be started yet.\r\nIf you have arrived early and wish for the customer to be sent an SMS requesting early acceptance, you must start the Onfleet job")) -> return context.getString(
                    R.string.this_job_is_not_due_to_be_started_yet
                )

                contains(convertToLowercase("Just in case we need to contact you before your flight")) -> return context.getString(
                    R.string.just_in_case_we_ned_to_contact_before_your_flight
                )

                equals(convertToLowercase("This is correct")) -> return context.getString(R.string.this_is_correct)
                equals(convertToLowercase("Yes")) -> return context.getString(R.string.yes)
                equals(convertToLowercase("No")) -> return context.getString(R.string.no)
                contains(convertToLowercase("Have a great flight to")) -> return context.getString(
                    R.string.have_a_great_flight_to,
                    message.substringAfter(convertToLowercase("Have a great flight to"))
                        .substringBefore(convertToLowercase("!"))
                )
                //CCD APIS TRANSLATION
                contains(convertToLowercase("Jobs entries not found")) -> return context.getString(R.string.jobs_entries_not_found)
                contains(convertToLowercase("No Actions found for Booking Reference:")) -> return context.getString(
                    R.string.no_actions_found_for_booking_reference,
                    message.substringAfter(convertToLowercase("Reference:"))
                        .substringBefore(convertToLowercase("and Booking")),
                    message.substringAfter(convertToLowercase("Reference"))
                )

                contains(convertToLowercase("Cannot determine the Live Location of Luggage")) -> return context.getString(
                    R.string.cannot_determine_the_live_location_of_luggage,
                    message.substringAfter(convertToLowercase("Luggage"))
                )

                contains(convertToLowercase("is null.")) -> return context.getString(
                    R.string.of_type_is_null,
                    message.substringBefore(convertToLowercase("of type")),
                    message.substringAfter(convertToLowercase("of type"))
                        .substringBefore(convertToLowercase("is null"))
                )

                contains(convertToLowercase("already Completed")) -> return context.getString(
                    R.string.action_already_completed,
                    message.substringAfter(convertToLowercase("Action:"))
                        .substringBefore(convertToLowercase("already"))
                )

                contains(convertToLowercase("is null, empty or invalid. Please fill the")) -> return context.getString(
                    R.string.is_null_empty_or_invalid_please_fill_the,
                    message.substringBefore(convertToLowercase("is null")),
                    message.substringAfter(convertToLowercase("fill the"))
                )

                contains(convertToLowercase("Job not found for Id:")) -> return context.getString(
                    R.string.job_not_found_for_id,
                    message.substringAfter(convertToLowercase("Id:"))
                )

                contains(convertToLowercase("User Not Found for Id:")) -> return context.getString(
                    R.string.user_not_found_for_id,
                    message.substringAfter(convertToLowercase("Id:"))
                )

                contains(convertToLowercase("Sorry the location you specified could not be found.")) -> return context.getString(
                    R.string.sorry_the_location_you_specified_could_not_be_found
                )

                contains(convertToLowercase("does not have permission to Update JobActivityStatus.")) -> return context.getString(
                    R.string.does_not_have_permission_to_update_job_activity_status,
                    message.substringAfter(convertToLowercase("UserId:"))
                        .substringBefore(convertToLowercase("does"))
                )

                contains(convertToLowercase("Image cannot be viewed because this is not a luggage/driver image.")) -> return context.getString(
                    R.string.image_cannot_be_viewed_because_this_is_not_a_luggage_driver_image
                )

                contains(convertToLowercase("No Booking Journeys found for Booking Journey Reference")) -> return context.getString(
                    R.string.no_booking_journeys_found_for_booking_journey_reference,
                    message.substringAfter(convertToLowercase("Reference"))
                )

                contains(convertToLowercase("already exists and has not been added.")) -> return context.getString(
                    R.string.tamper_aware_seal_code_already_exists_and_has_not_been_added,
                    message.substringAfter(convertToLowercase("Code:"))
                        .substringBefore(convertToLowercase("already"))
                )

                contains(convertToLowercase("Print tag failed because")) -> return context.getString(
                    R.string.print_tag_failed_because,
                    message.substringAfter(convertToLowercase("because"))
                )

                contains(convertToLowercase("Excess bag processing disabled")) -> return context.getString(
                    R.string.excess_bag_processing_disabled
                )

                contains(convertToLowercase("Unable to print the tag")) -> return context.getString(
                    R.string.unable_to_print_the_tag_because_the_tag_was_generated_manually,
                    message.substringAfter(convertToLowercase("tag"))
                        .substringBefore(convertToLowercase("because"))
                )

                contains(convertToLowercase("Tag already printed")) -> return context.getString(R.string.tag_already_printed)
                contains(convertToLowercase("Passenger not checked in")) -> return context.getString(
                    R.string.passenger_not_checked_in
                )

                contains(convertToLowercase("Itinerary does not match AirPortr records. Try manual mode")) -> return context.getString(
                    R.string.itinerary_does_not_match_airPortr_records_try_manual_mode
                )

                contains(convertToLowercase("Pax boarding pass(or equivalent) not found")) -> return context.getString(
                    R.string.pax_boarding_pass_or_equivalent_not_found
                )

                contains(convertToLowercase("Unable to print the tag as the seal code is empty.")) -> return context.getString(
                    R.string.unable_to_print_the_tag_as_the_seal_code_is_empty
                )

                contains(convertToLowercase("Passenger Luggage Not Found for Id:")) -> return context.getString(
                    R.string.passenger_luggage_not_found_for_id,
                    message.substringAfter(convertToLowercase("Id:"))
                )

                contains(convertToLowercase("has no bags")) -> return context.getString(
                    R.string.passenger_has_no_bags,
                    message.substringAfter(convertToLowercase("Passenger"))
                        .substringBefore(convertToLowercase("has"))
                )

                contains(convertToLowercase("No bags found for booking(s)")) -> return context.getString(
                    R.string.no_bags_found_for_booking,
                    message.substringAfter(convertToLowercase("booking(s)"))
                )

                contains(convertToLowercase("No bags available in storage booking(s)")) -> return context.getString(
                    R.string.no_bags_available_in_storage_booking,
                    message.substringAfter(convertToLowercase("booking(s)"))
                )

                contains(convertToLowercase("just now")) -> return context.getString(
                    R.string.just_now
                )

                contains(convertToLowercase("a minute ago")) -> return context.getString(
                    R.string.a_minute_ago
                )

                contains(convertToLowercase("an hour ago")) -> return context.getString(
                    R.string.an_hour_ago
                )

                contains(convertToLowercase("yesterday")) -> return context.getString(
                    R.string.yesterday
                )

                contains(convertToLowercase(" minutes ago")) -> return context.getString(
                    R.string.minutes_ago, message.substringBefore(convertToLowercase(" minutes"))
                )

                contains(convertToLowercase(" hours ago")) -> return context.getString(
                    R.string.hours_ago, message.substringBefore(convertToLowercase(" hours"))
                )

                contains(convertToLowercase(" days ago")) -> return context.getString(
                    R.string.days_ago, message.substringBefore(convertToLowercase(" days"))
                )

                contains(convertToLowercase("Unable to delete the tag because the bag has been injected.")) -> return context.getString(
                    R.string.unable_to_delete_the_tag_because_the_bag_has_been_inject
                )

                contains(convertToLowercase("Unable to delete the tag as the seal code")) -> return context.getString(
                    R.string.unable_to_delete_delete_the_tag
                )

                contains(convertToLowercase("You have 2 attempts remaining after which your account will be temporarily locked. If you are unsure please reset your password.")) -> return context.getString(
                    R.string.login_attempt_error2
                )

                contains(convertToLowercase("You have 1 attempt remaining after which your account will be temporarily locked. If you are unsure please reset your password.")) -> return context.getString(
                    R.string.login_attempt_error1
                )

                contains(convertToLowercase("Your account is now locked. Please reset your password or try again later")) -> return context.getString(
                    R.string.account_block_error
                )

                contains(convertToLowercase("Account is blocked")) -> return context.getString(
                    R.string.account_is_blocked
                )

                contains(convertToLowercase("Does your baggage contain electronic devices such as a laptop or chargeable speaker?")) -> return context.getString(
                    R.string.does_your_baggage
                )
            }
            return ""
        }
    }

    fun passengerDeclarationAllSetUpString(message: String): String {
        with(message.toLowerCase()) {
            when {
                contains(convertToLowercase("Have a great flight to")) -> return context.getString(
                    R.string.have_a_great_flight_to,
                    message.substringAfter(convertToLowercase("to "))
                )
            }
            return ""
        }
    }

    fun getAirlineText(message: String): String {
        val regex = Regex("(?<=set by )([\\w\\s]+)")
        val matchResult = regex.find(message)
        var airlineName = ""
        if (matchResult != null) {
            airlineName = matchResult.value
        } else {
            airlineName = ""
        }
        return airlineName
    }

    fun getPhoneNumber(message: String): String {
        val regex = "The 'To' number (.+?) is not a valid phone number.".toRegex()
        val matchResult = regex.find(message)
        val extractedNumber = matchResult?.groups?.get(1)?.value

        if (extractedNumber != null) {
            return extractedNumber
        } else {
            return ""
        }
    }

    fun getAttemptNumber(message: String): String {
        val regex =
            "You have (\\d+) attempt remaining after which your account will be temporarily locked.".toRegex()
        val matchResult = regex.find(message)
        val extractedAttempts = matchResult?.groups?.get(1)?.value

        if (extractedAttempts != null) {
            return extractedAttempts
        } else {
            return ""
        }
    }

    fun getRemainingTime(message: String): String {
        val regex = "Sorry, your code is only valid for (\\d+) minutes and has expired.".toRegex()
        val matchResult = regex.find(message)
        val extractedTime = matchResult?.groups?.get(1)?.value

        if (extractedTime != null) {
            return extractedTime
        } else {
            return ""
        }
    }

    fun convertToLowercase(word: String): String {
        return word.lowercase()
    }


    fun translationForExcessCharges(message: String, airLineName: String): String {
        var overWeightBag = ""
        var extraBag = ""
        if (message.contains("International flights may be subject to higher charges.")) {
            return context.getString(
                R.string.the_charges_are_set_by_and_at_the_discretion_of_airline,
                airLineName
            )
        } else {
            if (airLineName == "American Airlines") {
                overWeightBag =
                    message.substringAfter("Overweight bags are <b>")
                        .substringBefore("each</b>.")
                extraBag = message.substringAfter("Extra bags are <b>")
                    .substringBefore(" each</b> depending on the quantity")
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_american_airways,
                    airLineName,
                    overWeightBag,
                    extraBag
                )
            } else if (airLineName == "Air France") {
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_air_france
                )
            } else if (airLineName == "KLM") {
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_klm_airline
                )
            } else if (airLineName == "Virgin Atlantic") {
                overWeightBag =
                    message.substringAfter("Overweight bags are <b>")
                        .substringBefore("each</b>.")
                extraBag = message.substringAfter("Extra bags are <b>")
                    .substringBefore(" each</b> depending on the quantity")
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_virgin_atlantic,
                    airLineName,
                    overWeightBag,
                    extraBag
                )
            } else if (airLineName == "EasyJet") {
                overWeightBag =
                    message.substringAfter("Overweight bags are <b>")
                        .substringBefore("per kg")
                extraBag = message.substringAfter("Extra bags are <b>")
                    .substringBefore("</b> each.")
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_easyjet_airline,
                    overWeightBag,
                    extraBag
                )
            } else if (airLineName == "Austrian Airlines") {
                overWeightBag =
                    message.substringAfter("of ")
                        .substringBefore("</b>.")
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_austrain_airways,
                    overWeightBag
                )
            } else if (airLineName == "Lufthansa") {
                overWeightBag =
                    message.substringAfter("of ")
                        .substringBefore("</b>.")
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_lufthansa_airways,
                    overWeightBag
                )
            } else if (airLineName == "British Airways") {
                overWeightBag =
                    message.substringAfter("Overweight bags are <b>")
                        .substringBefore(" each</b>")
                extraBag = message.substringAfter("Extra bags are <b>")
                    .substringBefore("</b> each depending on your cabin")
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_british_airways,
                    airLineName,
                    overWeightBag,
                    extraBag
                )
            } else if (airLineName == "Singapore Airlines") {
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_singapore_airline
                )
            } else if (airLineName == "Emirates") {
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_emirates_airline
                )
            } else if (airLineName == "Swiss") {
                overWeightBag =
                    message.substringAfter("\n\nOverweight bags are ")
                        .substringBefore(" each in excess")
                extraBag = message.substringAfter("Extra bags are ")
                    .substringBefore(" each")
                var end = message.substringAfter("to ")
                    .substringBefore(" per")
                return context.getString(
                    R.string.the_charges_are_set_by_and_at_the_discretion_of_swiss_airways,
                    airLineName,
                    overWeightBag,
                    extraBag,
                    end
                )
            } else {
                return message
            }
        }
    }

    fun translationForExcessChargesHomeScreen(message: String, airLineName: String): String {
        if (airLineName == "OS") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_austrian
            )
        } else if (airLineName == "BA") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_british
            )
        } else if (airLineName == "AA") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags
            )
        } else if (airLineName == "LX") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_swiss
            )
        } else if (airLineName == "KL") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_klm_airlinee
            )
        } else if (airLineName == "AF") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_air_france
            )
        } else if (airLineName == "SQ") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_singapore_airlinee
            )
        } else if (airLineName == "U2") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_easyjet_airline
            )
        } else if (airLineName == "EZS") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_easyjet_airline
            )
        } else if (airLineName == "EZY") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_easyjet_airline
            )
        } else if (airLineName == "EK") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_emirates_airline
            )
        } else if (airLineName == "LH") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_lufthansa
            )
        } else if (airLineName == "VS") {
            return context.getString(
                R.string.i_understand_i_will_be_automatically_charged_if_any_of_my_bags_virgin_alantic
            )
        } else {
            return message
        }

    }

}
