import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:pankookidz/apidata/apidata.dart';
import 'package:pankookidz/home.dart';
import 'package:pankookidz/ui/multi_screen_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:wakelock/wakelock.dart';
import 'package:webview_flutter/webview_flutter.dart';

import '../global.dart';

class IFramePlayerPage extends StatefulWidget {
  IFramePlayerPage({this.url,});
  final String url;

  @override
  _IFramePlayerPageState createState() => _IFramePlayerPageState();
}

class _IFramePlayerPageState extends State<IFramePlayerPage> {
  final Completer<WebViewController> _controller =
  Completer<WebViewController>();
  var playerResponse;
  GlobalKey sc = new GlobalKey<ScaffoldState>();

  @override
  void dispose() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
      DeviceOrientation.landscapeLeft,
      DeviceOrientation.landscapeRight
    ]);
    super.dispose();
  }
  saveNewToken(token) async {
    prefs = await SharedPreferences.getInstance();
    prefs.setString('token', "$token");
  }

  getNewToken() async {
    // obtain shared preferences
    prefs = await SharedPreferences.getInstance();
    prefs.getString('token');
    nToken = prefs.getString('token');
  }


  screenLogout() async{
    Wakelock.disable();
    final screenLogOutResponse = await http.post( APIData.screenLogOutApi, body: {
      "screen": '$myActiveScreen',
      "count": '${fileContent['screenCount']}',
    }, headers: {
      // ignore: deprecated_member_use
      HttpHeaders.AUTHORIZATION: fullData
    });
    print(screenLogOutResponse.statusCode);
    print(screenLogOutResponse.body);

    final accessToken = await http.post(APIData.tokenApi, body: {
      "email": fileContent['user'],
      "password": fileContent['pass'],
    });


    if(accessToken.statusCode == 200){
      print("Logged In");
      var user = json.decode(accessToken.body);
      var newToken = "Bearer ${user['access_token']}";
      saveNewToken(newToken);
      getNewToken();
      setState(() {
        fullData = "Bearer ${user['access_token']}";
      });

    }
  }
  //  Handle back press
  Future<bool> onWillPopS() {
    print("Back Pressed");
    if(userPaymentType != "Free"){
      screenLogout();
    }
    DateTime now = DateTime.now();
    if (currentBackPressTime == null || now.difference(currentBackPressTime) > Duration(seconds: 2)) {
      currentBackPressTime = now;
      Navigator.pop(context);
      return Future.value(true);
    }
    return Future.value(true);
  }

  Future<void> didChangeAppLifecycleState(AppLifecycleState state) async {
    switch (state) {
      case AppLifecycleState.inactive:
        print("1000");
        screenLogout();
        break;
      case AppLifecycleState.paused:
        print("1001");
        screenLogout();
//        Navigator.pop(context);
        break;
      case AppLifecycleState.resumed:
        updateScreens(myActiveScreen, fileContent["screenCount"]);
        print("1003");
//        Navigator.pop(context);
        break;
      case AppLifecycleState.detached:
        screenLogout();
        // TODO: Handle this case.
        break;
    }
  }
  updateScreens(screen, count) async {
    final updateScreensResponse = await http.post(APIData.updateScreensApi, body: {
      "macaddress": '$ip',
      "screen": '$screen',
      "count": '$count',
    }, headers: {
      // ignore: deprecated_member_use
      HttpHeaders.AUTHORIZATION: nToken
    });
    print(updateScreensResponse.statusCode);
    print(fullData);
    print(updateScreensResponse.body);
    if (updateScreensResponse.statusCode == 200) {
      print(updateScreensResponse.body);
    }
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeLeft,
      DeviceOrientation.landscapeRight
    ]);
    double width;
    double height;
    width = MediaQuery.of(context).size.width;
    height = MediaQuery.of(context).size.height;
    JavascriptChannel _toasterJavascriptChannel(BuildContext context) {
      return JavascriptChannel(
          name: 'Toaster',
          onMessageReceived: (JavascriptMessage message) {
            Scaffold.of(context).showSnackBar(
              SnackBar(content: Text(message.message)),
            );
          });
    }
    return WillPopScope(
        child: Scaffold(
      key: sc,
      body: Container(
          width: width,
          height: height,
          child: WebView(
              initialUrl:  Uri.dataFromString(
                  '''
                    <html>
                    <body style="width:100%;height:100%;display:block;background:black;">
                    <iframe width="100%" height="100%" 
                    style="width:100%;height:100%;display:block;background:black;"
                    src="${widget.url}" 
                    frameborder="0" 
                    allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
                     allowfullscreen="allowfullscreen"
                      mozallowfullscreen="mozallowfullscreen" 
                      msallowfullscreen="msallowfullscreen" 
                      oallowfullscreen="oallowfullscreen" 
                      webkitallowfullscreen="webkitallowfullscreen"
                     >
                    </iframe>
                    </body>
                    </html>
                  ''',
                  mimeType: 'text/html',
                  encoding: Encoding.getByName('utf-8')
              ).toString(),
              javascriptMode: JavascriptMode.unrestricted,
              onWebViewCreated: (WebViewController webViewController) {
                _controller.complete(webViewController);
              },
              javascriptChannels: <JavascriptChannel>[
                _toasterJavascriptChannel(context),
              ].toSet()
          )
      ),
    ), onWillPop: onWillPopS);
  }
}

