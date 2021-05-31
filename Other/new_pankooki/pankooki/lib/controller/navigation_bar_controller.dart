import 'dart:convert';
import 'dart:io';

import 'package:data_connection_checker/data_connection_checker.dart';
import 'dart:async';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:pankookidz/apidata/apidata.dart';
import 'package:pankookidz/download/download_videos.dart';
import 'package:pankookidz/global.dart';
import 'package:pankookidz/page_home.dart';
import 'package:pankookidz/ui/custom_drawer.dart';
import 'package:pankookidz/ui/multi_screen_page.dart';
import 'package:pankookidz/ui/my_list.dart';
import 'package:pankookidz/ui/search.dart';
import 'package:http/http.dart' as http;

class BottomNavigationBarController extends StatefulWidget {
  BottomNavigationBarController({this.pageInd});
  final pageInd;

  @override
  _BottomNavigationBarControllerState createState() =>
      _BottomNavigationBarControllerState();
}

class _BottomNavigationBarControllerState extends State<BottomNavigationBarController> {
  int _selectedIndex;
  DataConnectionChecker dataConnectivity;
  StreamSubscription<DataConnectionStatus> listener;

  Future<String> getAllScreens() async {
    final getAllScreensResponse =
    await http.get(Uri.encodeFull(APIData.showScreensApi), headers: {
      // ignore: deprecated_member_use
      HttpHeaders.AUTHORIZATION: nToken == null ? fullData : nToken
    });
    print(getAllScreensResponse.statusCode);
    print(getAllScreensResponse.body);
    var screensRes = json.decode(getAllScreensResponse.body);
    setState(() {
      screen1 = screensRes['screen']['screen1'] == null
          ? "Screen1"
          : screensRes['screen']['screen1'];
      screen2 = screensRes['screen']['screen2'] == null
          ? "Screen2"
          : screensRes['screen']['screen2'];
      screen3 = screensRes['screen']['screen3'] == null
          ? "Screen3"
          : screensRes['screen']['screen3'];
      screen4 = screensRes['screen']['screen4'] == null
          ? "Screen4"
          : screensRes['screen']['screen4'];
    });
    setState(() {
      screenList = [screen1, screen2, screen3, screen4];
    });
    print("$screenList");
    return null;
  }

  static List<Widget> _widgetOptions = <Widget>[
    PageHome(),
    SearchResultList(),
    MyListPage(),
    OfflineDownloadPage(),
    CustomDrawer()
  ];

  @override
  void initState() {
    super.initState();
    _selectedIndex = widget.pageInd != null ? widget.pageInd : 0;
    dataConnectivity = new DataConnectionChecker();
    dataConnectivity.onStatusChange.listen((status) {
      switch (status) {
        case DataConnectionStatus.connected:
          _selectedIndex = widget.pageInd != null ? widget.pageInd : 0;
          break;
        case DataConnectionStatus.disconnected:
          _selectedIndex = widget.pageInd != null ? widget.pageInd : 3;
          var router = new MaterialPageRoute(
              builder: (BuildContext context) =>   OfflineDownloadPage());
          Navigator.of(context).push(router);
          break;
      }
    });
    if(status == "1"){
      if(userPaymentType != "Free"){
        getAllScreens();
      }
    }
  }


  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
        child: Scaffold(
            bottomNavigationBar: BottomNavigationBar(
              type: BottomNavigationBarType.fixed,
              backgroundColor: Colors.black,
              items: const <BottomNavigationBarItem>[
                BottomNavigationBarItem(title: Text("Home"), icon: Icon(Icons.home)),
                BottomNavigationBarItem(title: Text("Search"), icon: Icon(Icons.search)),
                BottomNavigationBarItem(title: Text("Wishlist"), icon: Icon(Icons.favorite_border)),
                BottomNavigationBarItem(title: Text("Download"), icon: Icon(Icons.file_download)),
                BottomNavigationBarItem(title: Text('Menu'), icon: Icon(Icons.menu)),
              ],
              currentIndex: _selectedIndex,
              selectedItemColor: Colors.white,
              unselectedLabelStyle: TextStyle(color: Colors.white),
              unselectedItemColor: Colors.grey,
              onTap: _onItemTapped,
            ),
            body: Center(
              child: _widgetOptions.elementAt(_selectedIndex),
            )
        ),
        onWillPop: onWillPopS);
  }
}

// Handle back press to exit
Future<bool> onWillPopS() {
  DateTime now = DateTime.now();
  if (currentBackPressTime == null ||
      now.difference(currentBackPressTime) > Duration(seconds: 2)) {
    currentBackPressTime = now;
    Fluttertoast.showToast(msg: "Press again to exit.");
    return Future.value(false);
  }
  return SystemNavigator.pop();
}
