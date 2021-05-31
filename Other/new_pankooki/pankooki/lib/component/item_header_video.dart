import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:get_ip/get_ip.dart';
import 'package:http/http.dart' as http;
import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';
import 'package:pankookidz/apidata/apidata.dart';
import 'package:pankookidz/download/download_videos.dart';
import 'package:pankookidz/global.dart';
import 'package:pankookidz/model/video_data.dart';
import 'package:pankookidz/player/iframe_player.dart';
import 'package:pankookidz/player/player.dart';
import 'package:pankookidz/player/m_player.dart';
import 'package:pankookidz/player/playerMovieTrailer.dart';
import 'package:pankookidz/player/trailer_cus_player.dart';
import 'package:pankookidz/ui/subscription.dart';
import 'package:pankookidz/utils/icons.dart';
import 'package:pankookidz/utils/item_header_diagonal.dart';
import 'package:pankookidz/utils/item_rating.dart';
import 'package:pankookidz/utils/item_video_box.dart';
import 'package:shared_preferences/shared_preferences.dart';

class VideoDetailHeader extends StatefulWidget {
  VideoDetailHeader(this.game);

  final VideoDataModel game;

  @override
  VideoDetailHeaderState createState() => VideoDetailHeaderState();
}

class VideoDetailHeaderState extends State<VideoDetailHeader> with WidgetsBindingObserver{
  Connectivity connectivity;


  // ignore: cancel_subscriptions
  StreamSubscription<ConnectivityResult> subscription;
  var _connectionStatus = 'Unknown';
  bool boolValue;
  var dMsg = '';
  var hdUrl;
  var sdUrl;
  var mReadyUrl, mIFrameUrl, mUrl360, mUrl480, mUrl720, mUrl1080, youtubeUrl, vimeoUrl;

  Future<void> initPlatformState() async {
    String ipAddress;
    try {
      ipAddress = await GetIp.ipAddress;
    } on PlatformException {
      ipAddress = 'Failed to get ipAddress.';
    }
    if (!mounted) return;
    setState(() {
      ip = ipAddress;
    });
    print("Mobile: Ip: $ip");
  }

  getValuesSF() async {
    prefs = await SharedPreferences.getInstance();
    setState(() {
      boolValue = prefs.getBool('boolValue');
    });
  }

  Future<void> _networkAlert(BuildContext context) {
    return showDialog<void>(
      context: context,
      builder: (BuildContext context) {
        return Center(
          child: Container(
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(40.0),
            ),
            child: AlertDialog(
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.all(Radius.circular(25.0))),
              backgroundColor: Color.fromRGBO(30, 30, 30, 1.0),
              contentPadding: const EdgeInsets.all(16.0),
              title: Text('Network Connection!'),
              content: const Text('You are using mobile network' +
                  '\n' +
                  'Change app setting or switch enable Wi-fi'),
              actions: <Widget>[
                FlatButton(
                  child: Text(
                    'Close',
                    style: TextStyle(fontSize: 16.0),
                  ),
                  onPressed: () {
                    getValuesSF();
                    Navigator.of(context).pop();
                  },
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  updateScreens(screen, count) async {
      final updateScreensResponse = await http.post(
          APIData.updateScreensApi, body: {
        "macaddress": '$ip',
        "screen": '$screen',
        "count": '$count',
      }, headers: {
        // ignore: deprecated_member_use
        HttpHeaders.AUTHORIZATION: nToken == null ? fullData : nToken
      });

      if (updateScreensResponse.statusCode == 200) {
        print(updateScreensResponse.body);
      }
  }

  getAllScreens(mVideoUrl, type) async {
    var screensRes;
    var resCode;
      final getAllScreensResponse = await http.get(Uri.encodeFull(APIData.showScreensApi), headers: {
        // ignore: deprecated_member_use
        HttpHeaders.AUTHORIZATION: nToken == null ? fullData : nToken
      });
      print(getAllScreensResponse.statusCode);
      print(getAllScreensResponse.body);
      resCode = getAllScreensResponse.statusCode;
      screensRes = json.decode(getAllScreensResponse.body);

    setState(() {
      screenUsed1 = screensRes['screen']['screen_1_used'];
      screenUsed2 = screensRes['screen']['screen_2_used'];
      screenUsed3 = screensRes['screen']['screen_3_used'];
      screenUsed4 = screensRes['screen']['screen_4_used'];

      activeScreen = screensRes['screen']['activescreen'];
      screenUsed1 = screensRes['screen']['screen_1_used'];
      screenUsed2 = screensRes['screen']['screen_2_used'];
      screenUsed3 = screensRes['screen']['screen_3_used'];
      screenUsed4 = screensRes['screen']['screen_4_used'];
    });

    if (resCode == 200) {
      if(fileContent["screenCount"] == "1"){
        if(screenUsed1 == "YES"){
          Fluttertoast.showToast(msg: "Profile already in use.");
          return false;
        }else{
          updateScreens(fileContent['screenName'], fileContent['screenCount']);
          if(type == "CUSTOM"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>
                new MyCustomPlayer(
                  url: mVideoUrl,
                  title: widget.game.name,
                  downloadStatus: 1,
                ));
            Navigator.of(context).push(router);
          }else if(type == "EMD"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>  IFramePlayerPage(url: mVideoUrl)
            );
            Navigator.of(context).push(router);
          }
          else if(type == "JS"){
            var router = new MaterialPageRoute(
              builder: (BuildContext context) => PlayerMovie(
                  id : widget.game.id,
                  type: widget.game.datatype
              ),
            );
            Navigator.of(context).push(router);
          }
        }
      }
      else if(fileContent["screenCount"] == "2"){
        if(screenUsed2 == "YES"){
          Fluttertoast.showToast(msg: "Profile already in use.");
          return false;
        } else{
          updateScreens(
              fileContent['screenName'],
              fileContent['screenCount']);
          if(type == "CUSTOM"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>
                new MyCustomPlayer(
                  url: mVideoUrl,
                  title: widget.game.name,
                  downloadStatus: 1,
                ));
            Navigator.of(context).push(router);
          }else if (type == "EMD"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>  IFramePlayerPage(url: mVideoUrl)
            );
            Navigator.of(context).push(router);
          }
          else if (type == "JS"){
            var router = new MaterialPageRoute(
              builder: (BuildContext context) => PlayerMovie(
                  id : widget.game.id,
                  type: widget.game.datatype
              ),
            );
            Navigator.of(context).push(router);
          }
        }
      }
      else if(fileContent["screenCount"] == "3"){
        if(screenUsed3 == "YES"){
          Fluttertoast.showToast(msg: "Profile already in use.");
          return false;
        }else{
          updateScreens(fileContent['screenName'], fileContent['screenCount']);
          if(type == "CUSTOM"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>
                new MyCustomPlayer(
                  url: mVideoUrl,
                  title: widget.game.name,
                  downloadStatus: 1,
                ));
            Navigator.of(context).push(router);
          }else if (type == "EMD"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>  IFramePlayerPage(url: mVideoUrl)
            );
            Navigator.of(context).push(router);
          }
          else if (type == "JS"){
            var router = new MaterialPageRoute(
              builder: (BuildContext context) => PlayerMovie(
                  id : widget.game.id,
                  type: widget.game.datatype
              ),
            );
            Navigator.of(context).push(router);
          }
        }
      }
      else if(fileContent["screenCount"] == "4"){
        if(screenUsed4 == "YES"){
          Fluttertoast.showToast(msg: "Profile already in use.");
          return false;
        }else{
          updateScreens(
              fileContent['screenName'],
              fileContent['screenCount']);
          if(type== "CUSTOM"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>
                new MyCustomPlayer(
                  url: mVideoUrl,
                  title: widget.game.name,
                  downloadStatus: 1,
                ));
            Navigator.of(context).push(router);
          }else if (type == "EMD"){
            var router = new MaterialPageRoute(
                builder: (BuildContext context) =>  IFramePlayerPage(url: mVideoUrl)
            );
            Navigator.of(context).push(router);
          }
          else if (type == "JS"){
            var router = new MaterialPageRoute(
              builder: (BuildContext context) => PlayerMovie(
                  id : widget.game.id,
                  type: widget.game.datatype
              ),
            );
            Navigator.of(context).push(router);
          }

          }
      }

    }
  }

  freeTrial(videoURL, type){
    if(type == "EMD"){
      var router = new MaterialPageRoute(
          builder: (BuildContext context) =>  IFramePlayerPage(url: mIFrameUrl)
      );
      Navigator.of(context).push(router);
    }else if(type == "CUSTOM"){
      print("cus");
      print("cus $videoURL");
      var router1 = new MaterialPageRoute(
          builder: (BuildContext context) =>
          new MyCustomPlayer(
            url: videoURL,
            title: widget.game.name,
            downloadStatus: 1,
          ));
      Navigator.of(context).push(router1);
    }else {
      var router = new MaterialPageRoute(
        builder: (BuildContext context) => PlayerMovie(
            id : widget.game.id,
            type: widget.game.datatype
        ),
      );
      Navigator.of(context).push(router);
    }

  }

  Future<void> didChangeAppLifecycleState(AppLifecycleState state) async {
    switch(state){
      case AppLifecycleState.inactive:
        print("Inactive");
        break;
      case AppLifecycleState.resumed:
        print("Resumed");
        // TODO: Handle this case.
        break;
      case AppLifecycleState.paused:
        print("Paused");
        // TODO: Handle this case.
        break;
      case AppLifecycleState.detached:
        print("Detached");
        // TODO: Handle this case.
        break;
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    if (status == "1") {
      if(userPaymentType != "Free"){
        print("MyActiveScreen1: ${fileContent['screenName']}");
        print("MyActiveScreen2: ${fileContent['screenStatus']}");
        print("MyActiveScreen3: ${fileContent['screenCount']}");
        initPlatformState();
      }
    }
    connectivity = new Connectivity();
    subscription = connectivity.onConnectivityChanged.listen((ConnectivityResult result) {
      _connectionStatus = result.toString();
      print(_connectionStatus);
      getValuesSF();
      checkConnectionStatus = result.toString();
      if (result == ConnectivityResult.wifi) {
        setState(() {
          _connectionStatus = 'Wi-Fi';
        });
      } else if (result == ConnectivityResult.mobile) {
        _connectionStatus = 'Mobile';
      } else {
        var router = new MaterialPageRoute(
          builder: (BuildContext context) => OfflineDownloadPage(
            key: PageStorageKey('Page4'),
          ),
        );
        Navigator.of(context).push(router);
      }
    }
    );
  }

  void _showMsg() {
    if (userPaypalHistory.length == 0 || userStripeHistory == null) {
      dMsg = "Watch unlimited movies, TV shows and videos in HD or SD quality."
          " You don't have subscribe.";
    } else {
      dMsg = "Watch unlimited movies, TV shows and videos in HD or SD quality."
          " You don't have any active subscription plan.";
    }
    // set up the button
    Widget cancelButton = FlatButton(
      child: Text(
        "Cancel",
        style: TextStyle(color: greenPrime, fontSize: 16.0),
      ),
      onPressed: () {
        Navigator.pop(context);
      },
    );

    Widget subscribeButton = FlatButton(
      child: Text(
        "Subscribe",
        style: TextStyle(color: greenPrime, fontSize: 16.0),
      ),
      onPressed: () {
        Navigator.pop(context);
        var router = new MaterialPageRoute(
            builder: (BuildContext context) => new SubscriptionPlan());
        Navigator.of(context).push(router);
      },
    );

    // set up the AlertDialog
    AlertDialog alert = AlertDialog(
      backgroundColor: Colors.white,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10.0)),
      contentPadding:
          EdgeInsets.only(top: 10.0, left: 16.0, right: 16.0, bottom: 0.0),
      title: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Text(
            "Subscribe Plans",
            style: TextStyle(color: Colors.black),
          ),
        ],
      ),
      content: Row(
        children: <Widget>[
          Flexible(
            flex: 1,
            fit: FlexFit.loose,
            child: Text(
              "$dMsg",
              style: TextStyle(
                color: Colors.black,
              ),
            ),
          )
        ],
      ),
      actions: [
        subscribeButton,
        cancelButton,
      ],
    );

    // show the dialog
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }

  void _onTapPlay() {
    print("Type: ${widget.game.datatype}");
    mIFrameUrl = widget.game.iFrameLink;
    print("Iframe: $mIFrameUrl");
    mReadyUrl = widget.game.readyUrl;
    print("Ready Url: $mReadyUrl");
    mUrl360 = widget.game.url360;
    print("Url 360: $mUrl360");
    mUrl480 = widget.game.url480;
    print("Url 480: $mUrl480");
    mUrl720 = widget.game.url720;
    print("Url 720: $mUrl720");
    mUrl1080 = widget.game.url1080;
    print("Url 1080: $mUrl1080");

    if (mUrl360 != "null" || mUrl480 != "null" || mUrl720 != "null" || mUrl1080 != "null") {
      print("Multi Quality");
      _showQualityDialog(mUrl360, mUrl480, mUrl720, mUrl1080);
    } else {
      if(mIFrameUrl != "null"){
        var matchIFrameUrl = mIFrameUrl.substring(0, 24);
        if(matchIFrameUrl == 'https://drive.google.com'){
          var ind = mIFrameUrl.lastIndexOf('d/');
          var t = "$mIFrameUrl".trim().substring(ind + 2);
          var rep = t.replaceAll('/preview', '');
          var newurl = "https://www.googleapis.com/drive/v3/files/$rep?alt=media&key=${APIData.googleDriveApi}";
          userPaymentType == "Free" ? freeTrial(newurl, "CUSTOM"): getAllScreens(newurl, "CUSTOM");
        }else{
          var router = new MaterialPageRoute(
              builder: (BuildContext context) =>  IFramePlayerPage(url: mIFrameUrl)
          );
          Navigator.of(context).push(router);
        }
      }
      else if(mReadyUrl != "null"){
        var matchUrl = mReadyUrl.substring(0, 23);
        var checkMp4 = mReadyUrl.substring(mReadyUrl.length - 4);
        var checkMpd = mReadyUrl.substring(mReadyUrl.length - 4);
        var checkWebm = mReadyUrl.substring(mReadyUrl.length - 5);
        var checkMkv = mReadyUrl.substring(mReadyUrl.length - 4);
        var checkM3u8 = mReadyUrl.substring(mReadyUrl.length - 5);

        if(matchUrl.substring(0, 18) == "https://vimeo.com/"){
          var router = new MaterialPageRoute(
            builder: (BuildContext context) => PlayerMovie(
                id : widget.game.id,
                type: widget.game.datatype
            ),
          );
          Navigator.of(context).push(router);
        }

        else if(matchUrl == 'https://www.youtube.com/embed'){
          var url= '${widget.game.readyUrl}';
          print("youtube En: ${widget.game.readyUrl}");
          var router = new MaterialPageRoute(
              builder: (BuildContext context) =>  IFramePlayerPage(url: url)
          );
          Navigator.of(context).push(router);
        }
        else if(matchUrl.substring(0, 23) == 'https://www.youtube.com'){
          userPaymentType == "Free" ? freeTrial(widget.game.readyUrl, "JS"): getAllScreens(widget.game.readyUrl, "JS");
        }
        else if(checkMp4 == ".mp4" || checkMpd == ".mpd" ||
            checkWebm == ".webm" || checkMkv == ".mkv" ||
            checkM3u8 == ".m3u8"){
          userPaymentType == "Free" ? freeTrial(widget.game.readyUrl, "CUSTOM"): getAllScreens(widget.game.readyUrl, "CUSTOM");
        }
        else{
          userPaymentType == "Free" ? freeTrial(widget.game.readyUrl, "JS"): getAllScreens(widget.game.readyUrl, "JS");
        }
      }
    }
  }

  void _showQualityDialog(mUrl360, mUrl480, mUrl720, mUrl1080) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.all(Radius.circular(10.0))),
            backgroundColor: Color.fromRGBO(250, 250, 250, 1.0),
            title: Text("Video Quality", style: TextStyle(
                color: Color.fromRGBO( 72, 163, 198, 1.0),
                fontWeight: FontWeight.w600,
                fontSize: 20.0),
              textAlign: TextAlign.center,),
            content: Container(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.stretch,
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  Text("Available video Format in which you want to play video.",
                    style: TextStyle(
                        color: Colors.black.withOpacity(0.7),
                        fontSize: 12.0
                    ),
                  ),
                  SizedBox(
                    height: 5.0,
                  ),
                  mUrl360 == "null"? SizedBox.shrink() : Padding(
                    padding: EdgeInsets.only(
                        left: 50.0,
                        right: 50.0
                    ),
                    child: RaisedButton(
                      hoverColor: Colors.red,
                      splashColor: Color.fromRGBO(49, 131, 41, 1.0),
                      highlightColor: Color.fromRGBO( 72, 163, 198, 1.0),
                      color: greenPrime,
                      child: Container(
                        alignment: Alignment.center,
                        width: 100.0,
                        height: 30.0,
                        child: Text("360"),
                      ),
                      onPressed: (){
                        Navigator.pop(context);
                        if(userPaymentType == "Free"){
                          freeTrial(mUrl360, "CUSTOM");
                        }else{
                          getAllScreens(mUrl360, "CUSTOM");
                        }
                      },
                    ),
                  ),
                  SizedBox(
                    height: 5.0,
                  ),
                  mUrl480 == "null"? SizedBox.shrink() :  Padding(
                    padding: EdgeInsets.only(
                        left: 50.0,
                        right: 50.0
                    ),
                    child: RaisedButton(
                      color: greenPrime,
                      hoverColor: Colors.red,
                      splashColor: Color.fromRGBO(49, 131, 41, 1.0),
                      highlightColor: Color.fromRGBO( 72, 163, 198, 1.0),
                      child: Container(
                        alignment: Alignment.center,
                        width: 100.0,
                        height: 30.0,
                        child: Text("480"),
                      ),
                      onPressed: (){
                        Navigator.pop(context);
                        if(userPaymentType == "Free"){
                          freeTrial(mUrl480, "CUSTOM");

                        }else{
                          getAllScreens(mUrl480, "CUSTOM");
                        }
                      },
                    ),
                  ),
                  SizedBox(
                    height: 5.0,
                  ),
                  mUrl720 == "null"? SizedBox.shrink() :  Padding(
                    padding: EdgeInsets.only(
                        left: 50.0,
                        right: 50.0
                    ),
                    child: RaisedButton(
                      color: greenPrime,
                      hoverColor: Colors.red,
                      splashColor: Color.fromRGBO(49, 131, 41, 1.0),
                      highlightColor: Color.fromRGBO( 72, 163, 198, 1.0),
                      child: Container(
                        alignment: Alignment.center,
                        width: 100.0,
                        height: 30.0,
                        child: Text("720"),
                      ),
                      onPressed: (){
                        Navigator.pop(context);
                        if(userPaymentType == "Free"){
                          freeTrial(mUrl720, "CUSTOM");
                        }else{
                          getAllScreens(mUrl720, "CUSTOM");
                        }
                      },
                    ),
                  ),
                  SizedBox(
                    height: 5.0,
                  ),
                  mUrl1080 == "null"? SizedBox.shrink() :  Padding(
                    padding: EdgeInsets.only(
                        left: 50.0,
                        right: 50.0
                    ),
                    child: RaisedButton(
                      color: greenPrime,
                      hoverColor: Colors.red,
                      splashColor: Color.fromRGBO(49, 131, 41, 1.0),
                      highlightColor: Color.fromRGBO( 72, 163, 198, 1.0),
                      child: Container(
                        alignment: Alignment.center,
                        width: 100.0,
                        height: 30.0,
                        child: Text("1080"),
                      ),
                      onPressed: (){
                        Navigator.pop(context);
                        if(userPaymentType == "Free"){
                          freeTrial(mUrl1080, "CUSTOM");
                        }else{
                          getAllScreens(mUrl1080, "CUSTOM");
                        }
                      },
                    ),
                  ),
                  SizedBox(
                    height: 5.0,
                  ),
                ],
              ),
            )
        );
      },
    );
  }

  void _onTapTrailer() {
    var checkMp4 = widget.game.url.substring(widget.game.url.length - 4);
    var checkMpd = widget.game.url.substring(widget.game.url.length - 4);
    var checkWebm = widget.game.url.substring(widget.game.url.length - 5);
    var checkMkv = widget.game.url.substring(widget.game.url.length - 4);
    var checkM3u8 = widget.game.url.substring(widget.game.url.length - 5);
    if(widget.game.url.substring(0, 23) == 'https://www.youtube.com'){
      var router = new MaterialPageRoute(
          builder: (BuildContext context) => new PlayerMovieTrailer(
              id: widget.game.id, type: widget.game.datatype));
      Navigator.of(context).push(router);
    }else if(checkMp4 == ".mp4" || checkMpd == ".mpd" ||
        checkWebm == ".webm" || checkMkv == ".mkv" ||
        checkM3u8 == ".m3u8"){
      var router = new MaterialPageRoute(
          builder: (BuildContext context) =>
          new TrailerCustomPlayer(
            url: widget.game.url,
            title: widget.game.name,
            downloadStatus: 1,
          ));
      Navigator.of(context).push(router);
    }else {
      var router = new MaterialPageRoute(
          builder: (BuildContext context) => new PlayerMovieTrailer(
              id: widget.game.id, type: widget.game.datatype));
      Navigator.of(context).push(router);
    }
  }

  @override
  Widget build(BuildContext context) {
    var theme = Theme.of(context);
    return Stack(
      children: <Widget>[
        new Padding(
          padding: const EdgeInsets.only(bottom: 130.0),
          child: _buildDiagonalImageBackground(context),
        ),
        headerDecorationContainer(),
        new Positioned(
          top: 26.0,
          left: 4.0,
          child: new BackButton(color: Colors.white),
        ),
        new Positioned(
          top: 180.0,
          bottom: 0.0,
          left: 16.0,
          right: 16.0,
          child: headerRow(theme),
        ),
      ],
    );
  }

  Widget headerRow(theme) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        new Hero(
            tag: widget.game.name,
            child: new VideoBoxItem(
              context,
              widget.game,
              height: 220.0,
            )),
        new Expanded(
          child: new Padding(
            padding: const EdgeInsets.only(left: 16.0),
            child: new Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                new Text(
                  widget.game.name,
                  style: Theme.of(context).textTheme.subhead,
                  maxLines: 3,
                  overflow: TextOverflow.fade,
                ),
                Flex(
                  direction: Axis.horizontal,
                  children: <Widget>[
                    Flexible(
                      flex: 1,
                      child: widget.game.rating == null? SizedBox.shrink() : RatingInformation(widget.game),
                    )
                  ],
                ),
                header(theme),
              ],
            ),
          ),
        ),
      ],
    );
  }

  Widget headerDecorationContainer() {
    return Container(
      height: 262.0,
      decoration: BoxDecoration(
          //                  color: Colors.white,
          gradient: LinearGradient(
              begin: FractionalOffset.topCenter,
              end: FractionalOffset.bottomCenter,
              colors: [
            Color.fromRGBO(34, 34, 34, 1.0).withOpacity(0.1),
            Color.fromRGBO(34, 34, 34, 1.0),
          ],
              stops: [
            0.3,
            0.8
          ])),
    );
  }

  Widget header(theme) {
    return Padding(
        padding: const EdgeInsets.only(top: 12.0),
        child: new Column(
          children: <Widget>[
            widget.game.datatype == 'M'
                ? OutlineButton(
                    onPressed: status == "1" ? _onTapPlay : _showMsg,
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Expanded(
                          flex: 0,
                          child: Icon(Icons.play_arrow,
                              color: Color.fromRGBO(72, 163, 198, 1.0)),
                        ),
                        new Padding(
                          padding:
                              const EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                        ),
                        Expanded(
                          flex: 1,
                          child: new Text(
                            "Play",
                            textAlign: TextAlign.center,
                            style: TextStyle(
                                fontFamily: 'Lato',
                                fontSize: 15.0,
                                fontWeight: FontWeight.w800,
                                letterSpacing: 0.9,
                                color: Colors.white
                                // color: Colors.white
                                ),
                          ),
                        ),
                      ],
                    ),
                    padding: const EdgeInsets.fromLTRB(6.0, 0.0, 12.0, 0.0),
                    shape: new RoundedRectangleBorder(
                        borderRadius: new BorderRadius.circular(10.0)),
                    borderSide: new BorderSide(
                        color: Color.fromRGBO(72, 163, 198, 1.0), width: 2.0),
                    color: Color.fromRGBO(72, 163, 198, 1.0),
                    highlightColor: theme.accentColor,
                    highlightedBorderColor: theme.accentColor,
                    splashColor: Colors.black12,
                    highlightElevation: 0.0,
                  )
                : SizedBox.shrink(),
            widget.game.datatype == 'M'
                ? widget.game.url == null ? SizedBox.shrink() : OutlineButton(
                    onPressed: _onTapTrailer,
                    child: new Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Expanded(
                          flex: 0,
                          child:
                              new Icon(playOutlineIcon, color: Colors.white70),
                        ),
                        new Padding(
                          padding:
                              const EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                        ),
                        Expanded(
                          flex: 1,
                          child: new Text(
                            "Trailer",
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              fontFamily: 'Lato',
                              fontSize: 15.0,
                              fontWeight: FontWeight.w800,
                              letterSpacing: 0.9,
                              color: Colors.white,
                              // color: Colors.white
                            ),
                          ),
                        ),
                      ],
                    ),
                    padding: const EdgeInsets.fromLTRB(6.0, 0.0, 12.0, 0.0),
                    shape: new RoundedRectangleBorder(
                        borderRadius: new BorderRadius.circular(10.0)),
                    borderSide:
                        new BorderSide(color: Colors.white70, width: 2.0),
                    highlightColor: theme.accentColor,
                    highlightedBorderColor: theme.accentColor,
                    splashColor: Colors.black12,
                    highlightElevation: 0.0,
                  )
                : SizedBox.shrink()
          ],
        ));
  }

  Widget _buildDiagonalImageBackground(BuildContext context) {
    var screenWidth = MediaQuery.of(context).size.width;

    return new DiagonallyCutColoredImage(
      new FadeInImage.assetNetwork(
        image: widget.game.cover,
        placeholder: "assets/placeholder_cover.jpg",
        width: screenWidth,
        height: 260.0,
        fit: BoxFit.cover,
      ),
      color: const Color(0x00FFFFFF),
    );
  }
}
