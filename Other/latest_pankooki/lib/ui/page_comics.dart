import 'dart:async';
import 'dart:convert';
import 'dart:developer';
import 'dart:io';

import 'package:data_connection_checker/data_connection_checker.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;
import 'package:pankookidz/apidata/apidata.dart';
import 'package:pankookidz/download/download_videos.dart';
import 'package:pankookidz/global.dart';
import 'package:shimmer/shimmer.dart';
import 'package:wakelock/wakelock.dart';

import 'comic_list.dart';

class ComicsPage extends StatefulWidget {
  ComicsPage({Key key, this.mId}) : super(key: key);
  final mId;
  List<dynamic> comics;

  @override
  _ComicsPageState createState() => new _ComicsPageState();
}

class _ComicsPageState extends State<ComicsPage>
    with SingleTickerProviderStateMixin, RouteAware {
  var refreshKey = GlobalKey<RefreshIndicatorState>();

  DataConnectionChecker dataConnectivity;

  @override
  void initState() {
    menuDataArray = null;
    this.getMenuData();
    super.initState();
    this.refreshList();
    Wakelock.enable();

    dataConnectivity = new DataConnectionChecker();
    dataConnectivity.onStatusChange.listen((status) {
      switch (status) {
        case DataConnectionStatus.connected:
          break;
        case DataConnectionStatus.disconnected:
          var router = new MaterialPageRoute(
              builder: (BuildContext context) => OfflineDownloadPage(
                    key: PageStorageKey('Page4'),
                  ));
          Navigator.of(context).push(router);
          break;
      }
    });
  }

//  Used to refresh the list of movies and TV series on the home page
  Future<Null> refreshList() async {
    refreshKey.currentState?.show();
    await Future.delayed(Duration(seconds: 2));
    getMenuData();
  }

//  This future will return all the data related to particular menu.
  Future<String> getMenuData() async {
    try {
      final menuData = await http.get(Uri.encodeFull(APIData.comics), headers: {
        // ignore: deprecated_member_use
        HttpHeaders.AUTHORIZATION: nToken == null ? fullData : nToken
      });

      menuDataResponse = json.decode(menuData.body);
      if (mounted) {
        setState(() {
          menuDataArray = menuDataResponse['comics'];
          widget.comics = menuDataResponse['comics'];
        });
      }
      log("page_comics " + menuDataArray.toString());
      menuDataListLength = menuDataArray == null ? 0 : menuDataArray.length;

      menuDataResponse = json.decode(menuData.body);
      menuDataArray = menuDataResponse["comics"];
    } on SocketException catch (_) {
      var router = new MaterialPageRoute(
        builder: (BuildContext context) => OfflineDownloadPage(
          key: PageStorageKey('Page4'),
        ),
      );
      Navigator.of(context).push(router);
      print('not connected');
    }

    return null;
  }

  final ScrollController _scrollController = ScrollController();

/*  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        controller: _scrollController,
        slivers: <Widget>[
          SliverGrid(
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 3,
              childAspectRatio: 0.8,
            ),
            delegate: SliverChildBuilderDelegate(
              (BuildContext context, int index) {
                return Card(
                  child: GestureDetector(
                    onTap: () {
                      if (menuDataArray[index]['upload_pdf'] != null &&
                          menuDataArray[index]['title'] != null) {
                        var router = new MaterialPageRoute(
                            builder: (BuildContext context) => ViewComicsPage(
                                  name: menuDataArray[index]['upload_pdf'],
                                  title: menuDataArray[index]['title'],
                                ));
                        Navigator.of(context).push(router);
                      } else {
                        Fluttertoast.showToast(
                            msg: "Something went wrong",
                            toastLength: Toast.LENGTH_SHORT,
                            gravity: ToastGravity.CENTER,
                            backgroundColor: Colors.red,
                            textColor: Colors.white,
                            fontSize: 16.0);
                      }
                    },
                    //name: menuDataArray[index]['upload_pdf']
                    child: Container(
                      color: Color.fromRGBO(34, 34, 34, 1.0),
                      child: Padding(
                        padding: EdgeInsets.symmetric(vertical: 0.0),
                        child: Center(
                          child: Image.network(
                            "http://pankookidz.novuslogic.in/images/comics/pdf/thumbnails/" +
                                menuDataArray[index]['thumbnail'],
                            fit: BoxFit.cover,
                          ),
                        ),
                      ),
                    ),
                  ),
                );
              },
              childCount:menuDataArray.length,
            ),
          ),
        ],
      ),
    );
  }*/
  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    return new Container(
      child: RefreshIndicator(
        key: refreshKey,
        child: menuDataListLength == 0 ||
                menuDataArray == null ||
                menuDataArray[0] == "No Data Found"
            ?
//      For Shimmer
            shimmer(width)
            : tvAndMoviesColumn(),
        onRefresh: refreshList,
      ),
      color: Color.fromRGBO(34, 34, 34, 1.0),
    );
  }

// shimmer
  Widget shimmer(width) {
    return SingleChildScrollView(
        child:
            new Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
//                  Image slider shimmer
      //sliderImageShimmer(width),

      new Padding(
          padding: const EdgeInsets.only(
              top: 10.0, bottom: 0.0, left: 0.0, right: 0.0),
          child: new ListView.builder(
              shrinkWrap: true,
              itemCount: 3,
              physics: ClampingScrollPhysics(),
              primary: true,
              scrollDirection: Axis.vertical,
              padding: const EdgeInsets.only(top: 0.0),
              itemBuilder: (BuildContext context, int index) {
                return new Padding(
                    padding: const EdgeInsets.only(
                        top: 0.0, bottom: 0.0, left: 0.0, right: 0.0),
                    child: new Column(children: <Widget>[
//                                  Heading text shimmer
                      headingTextShimmer(),
//                                  All data images shimmer
                      videoImageShimmer(width),
                    ]));
              }))
    ]));
  }

// List tv series and movies
  Widget tvSeriesAndMoviesList() {
    return SizedBox(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      child: Padding(
        padding: const EdgeInsets.only(
            top: 10.0, bottom: 0.0, left: 0.0, right: 0.0),
        child: Scaffold(
          body: CustomScrollView(
            controller: _scrollController,
            slivers: <Widget>[
              SliverGrid(
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 3,
                  childAspectRatio: 0.8,
                ),
                delegate: SliverChildBuilderDelegate(
                  (BuildContext context, int index) {
                    return Card(
                      child: GestureDetector(
                        onTap: () {
                          log(menuDataArray[index].toString());
                          if (menuDataArray[index]['upload_pdf'] != null &&
                              menuDataArray[index]['title'] != null) {
                            var router = new MaterialPageRoute(
                                builder: (BuildContext context) =>
                                    ViewComicsPage(
                                      name: menuDataArray[index]['upload_pdf'],
                                      title: menuDataArray[index]['title'],
                                    ));
                            Navigator.of(context).push(router);
                          } else {
                            Fluttertoast.showToast(
                                msg: "Something went wrong",
                                toastLength: Toast.LENGTH_SHORT,
                                gravity: ToastGravity.CENTER,
                                backgroundColor: Colors.red,
                                textColor: Colors.white,
                                fontSize: 16.0);
                          }
                        },
                        //name: menuDataArray[index]['upload_pdf']
                        child: Container(
                          color: Color.fromRGBO(34, 34, 34, 1.0),
                          child: Padding(
                            padding: EdgeInsets.symmetric(vertical: 0.0),
                            child: Center(
                              child: Image.network(
                                "https://pankookidz.com/images/comics/pdf/thumbnails/" +
                                    menuDataArray[index]['thumbnail'],
                                fit: BoxFit.cover,
                              ),
                            ),
                          ),
                        ),
                      ),
                    );
                  },
                  childCount: menuDataArray.length,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

// tv series and movies column
  Widget tvAndMoviesColumn() {
    return SingleChildScrollView(
        child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        // imageSliderContainer(),

//              TV And Movie
        Padding(
          padding: const EdgeInsets.only(
              top: 10.0, bottom: 0.0, left: 0.0, right: 0.0),
          child: tvSeriesAndMoviesList(),
        ),

//             For All Genre
        Padding(
          padding: const EdgeInsets.only(
              top: 0.0, bottom: 0.0, left: 0.0, right: 0.0),
          //    child: allGenresList(),
        ),
      ],
    ));
  }

//  Heading text shimmer
  Widget headingTextShimmer() {
    return Container(
      child: new Padding(
        padding: const EdgeInsets.fromLTRB(16.0, 12.0, 8.0, 5.0),
        child: new Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            new Container(
              child: new Container(
                child: new ClipRRect(
                  borderRadius: new BorderRadius.circular(8.0),
                  child: new SizedBox(
                    child: Shimmer.fromColors(
                      baseColor: Color.fromRGBO(45, 45, 45, 1.0),
                      highlightColor: Color.fromRGBO(50, 50, 50, 1.0),
                      child: Card(
                        elevation: 0.0,
                        color: Color.fromRGBO(45, 45, 45, 1.0),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.all(
                            Radius.circular(10),
                          ),
                        ),
                        clipBehavior: Clip.antiAliasWithSaveLayer,
                        child: SizedBox(
                          width: 220,
                          height: 8,
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

// images shimmer
  Widget videoImageShimmer(width) {
    return   SizedBox(
        height: MediaQuery.of(context).size.height,
        width: MediaQuery.of(context).size.width,
        child: ListView.builder(
            itemCount: 10,
            scrollDirection: Axis.horizontal,
            padding: const EdgeInsets.only(left: 16.0, top: 4.0),
            itemBuilder: (BuildContext context, int position) {
              return new Padding(
                padding: const EdgeInsets.only(right: 12.0),
                child: new InkWell(
                  onTap: () {},
                  child: new Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      new Container(
                        child: new Container(
                          child: new ClipRRect(
                            borderRadius: new BorderRadius.circular(8.0),
                            child: new SizedBox(
                              child: Shimmer.fromColors(
                                baseColor: Color.fromRGBO(45, 45, 45, 1.0),
                                highlightColor: Color.fromRGBO(50, 50, 50, 1.0),
                                child: Card(
                                  elevation: 0.0,
                                  color: Color.fromRGBO(45, 45, 45, 1.0),
                                  shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.all(
                                      Radius.circular(10),
                                    ),
                                  ),
                                  clipBehavior: Clip.antiAliasWithSaveLayer,
                                  child: SizedBox(
                                    width: 100,
                                    height: 160,
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              );
            }));
  }
}
