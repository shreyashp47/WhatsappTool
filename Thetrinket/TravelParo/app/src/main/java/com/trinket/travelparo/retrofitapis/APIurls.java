package com.taxivaxi.employee.retrofitapis;



public class APIurls {

    static final String TEST_URL="http://corporate.taxivaxi.com/testmapi/v2_1/";
    static final String LIVE_URL="http://corporate.taxivaxi.com/mapi/v2_1/";
    static final String BASE_URL=LIVE_URL;
    static final String LOGIN="employeeLogin";
    static final String LOGOUT="employeeLogout";

    static final String updateFcmToken="updateFCMRegID";

    static final String ANALYTICS="employeehome";
//BOOKING URLs
    static final String TAXIBUSBOOKINGS ="getAllEmployeeBookings";
    static final String HOTELBOOKINGS="hotels/getAllEmployeeHotelBookings";
    static final String TRAINBOOKINGS="trains/getAllEmployeeTrainBookings";
    static final String PLANEBOOKINGS ="flights/getAllEmployeeFlightBookings";
    static final String FRROBOOKINGS ="frros/getAllEmployeeFrroBookings";



///BOOKING DETAILS URLs
    static final String TAXIBOOKINGDETAILS="viewBookingTaxiVaxi";
    static final String HOTELBOOKINGDETAILS="hotels/viewHotelBooking";
    static final String busBookingDetailsURL ="viewBusBookingTaxivaxi";
    static final String trainBookingDetailsURL ="trains/viewTrainBooking";
    static final String PLANEBookingDetailsURL ="flights/viewFlightBooking";
    static final String FRRODETAILS ="frros/viewFrroBooking";

    //FRRO
    static final String ADDFRRO ="frros/addFrroBooking";
    static final String getFrroBookingActionLogs ="frros/getFrroBookingActionLogs";
    static final String getFrroBookingEmployeeDocument ="frros/getFrroBookingEmployeeDocument";
    static final String getAllFrroRequestType ="frros/getAllFrroRequestType";
    static final String getFRRORulesToDisplay ="frros/getFRRORulesToDisplay";
    static final String saveFrroDocumentGetPath ="frros/saveFrroDocumentGetPath";
    static final String changeDocumentFRROBooking ="frros/changeDocumentFRROBooking";
    static final String addFrroBooking ="frros/addFrroBooking";
    public static final String CITIES = "getAllCities";
    public static final String addFrroBookings =  BASE_URL +"frros/addFrroBooking";
    public static final String getFrroBookingRequestLatters ="frros/getFrroBookingRequestLatters";
    public static final String getFrroBookingOfficeStatus ="frros/getFrroBookingOfficeStatus";
    public static final String getFRRODocumentListByRequestType = "frros/getFRRODocumentListByRequestType";
     public static final String changeDocumentLetterFRROBooking = "frros/changeDocumentLetterFRROBooking";
    public static final String changeRequestLetterSpocFRROBooking = "frros/changeRequestLetterSpocFRROBooking";
    public static final String getEmployeeQuestionnaireDetails = "frros/getEmployeeQuestionnaireDetails";
    ///FEEDBACK URLs
    static final String TAXIFEEDBACK="addTaxiEmployeeFeedback";
    static final String HOTELFEEDBACK="hotels/addHotelEmployeeFeedback";

    static final String SENDTRACKINGLINKURL="sendTrackingLink";
    static final String APK_VERSION_URL="getEmployeeAppVersion";

    public static final String cancelSpocFrroBooking =  BASE_URL +"frros/cancelSpocFrroBooking";

}
