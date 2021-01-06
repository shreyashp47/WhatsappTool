package com.taxivaxi.operator.retrofit;



public class ApiURLs {
    static final String liveUrl="https://corporate.taxivaxi.com/mapi/v2_1/operators/";
    static final String testUrl="https://corporate.taxivaxi.com/testmapi/v2_1/operators/";
    static final String baseURL=liveUrl;
    static final String googleApiBaseURL="https://maps.googleapis.com/maps/api/";

    static final String loginURL="login";
    static final String logout="logout";
    static final String upcomingBookingURL="getUpComingBookings";
    static final String driverStartedURL="driver_started";
    static final String driverArrived="arrived";
    static final String rideStarted="start_ride";
    static final String endRideURL="end_ride";
    static final String addFirebaseKey="add_firebase_key";
    static final String archivedBookingURL="getPastBookings";
    static final String uploadDutySlip="uploadDutySlip";

    static final String updateFcmToken="updateFCMRegID";
    static final String getPendingDutySlipBookings="getPendingDutySlipBookings";

    static final String directionApiURL="directions/json?";
    static final String distanceApiURL="distancematrix/json?";
}
