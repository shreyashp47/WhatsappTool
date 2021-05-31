import 'dart:io';
import 'dart:async';
import 'dart:convert';
import 'package:data_connection_checker/data_connection_checker.dart';
import 'package:flutter/material.dart';
import 'package:pankookidz/apidata/apidata.dart';
import 'package:pankookidz/controller/scroll_horizontal_video.dart';
import 'package:pankookidz/controller/scroll_horizontal_genre.dart';
import 'package:pankookidz/download/download_videos.dart';
import 'package:pankookidz/global.dart';
import 'package:pankookidz/model/video_data.dart';
import 'package:http/http.dart' as http;
import 'package:pankookidz/model/seasons.dart';
import 'package:pankookidz/widget/image_slider.dart';
import 'package:shimmer/shimmer.dart';
import 'package:wakelock/wakelock.dart';
import 'package:pankookidz/model/comments_model.dart';
import 'package:pankookidz/ui/grid_video_page_mt.dart';
import 'package:pankookidz/ui/grid_videos_page.dart';

class VideosPage extends StatefulWidget {
  VideosPage({Key key, this.mId}) : super(key: key);
  final mId;

  @override
  _VideosPageState createState() => new _VideosPageState();
}

class _VideosPageState extends State<VideosPage> with SingleTickerProviderStateMixin, RouteAware {
  List data1;
  GlobalKey _keyRed = GlobalKey();
  var refreshKey = GlobalKey<RefreshIndicatorState>();
  ScrollController controller;
  HorizontalVideoController moviecontroller;
  var _connectionStatus = 'Unknown';
  DataConnectionChecker dataConnectivity;
  StreamSubscription<DataConnectionStatus> listener;

  @override
  void initState() {
    menuDataArray = null;
    this.getMenuData();
    super.initState();
    this.refreshList();
    Wakelock.enable();
    controller = ScrollController(initialScrollOffset: 50.0);

    dataConnectivity = new DataConnectionChecker();
    dataConnectivity.onStatusChange.listen((status) {
      switch (status) {
        case DataConnectionStatus.connected:
          break;
        case DataConnectionStatus.disconnected:
          var router = new MaterialPageRoute(
              builder: (BuildContext context) =>  OfflineDownloadPage(
                key: PageStorageKey('Page4'),
              )
          );
          Navigator.of(context).push(router);
          break;
      }});
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
      final menuData = await http.get(
          Uri.encodeFull(APIData.menuDataApi + "/${widget.mId}"),
          headers: {
            // ignore: deprecated_member_use
            HttpHeaders.AUTHORIZATION: nToken == null ? fullData : nToken
          });

      final mainResponse =
      await http.get(Uri.encodeFull(APIData.allDataApi), headers: {
        // ignore: deprecated_member_use
        HttpHeaders.AUTHORIZATION: nToken == null ? fullData : nToken
      }
      );

      mainData = json.decode(mainResponse.body);

      menuDataResponse = json.decode(menuData.body);
    if(mounted){
      setState(() {
        menuDataArray = menuDataResponse['data'];
      });
    }
      menuDataListLength = menuDataArray == null ? 0 : menuDataArray.length;
      searchIds.clear();
      if (menuDataListLength != null) {
        for (var all = 0; all < menuDataListLength; all++) {
          for (var f in menuDataArray[all]) {
            searchIds.add(f);
          }
        }
      }

      menuDataResponse = json.decode(menuData.body);
      menuDataArray = menuDataResponse["data"];

      if (mounted) {
        this.setState(() {
          genreData = mainData['genre'];
        });
      }
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

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

//  Heading text shimmer
  Widget headingTextShimmer(){
    return Container(
      child: new Padding(
        padding: const EdgeInsets.fromLTRB(16.0, 12.0, 8.0, 5.0),
        child: new Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            new Container(
              child: new Container(
                child: new ClipRRect(
                  borderRadius:
                  new BorderRadius.circular(8.0),
                  child: new SizedBox(
                    child: Shimmer
                        .fromColors(
                      baseColor: Color.fromRGBO(45, 45, 45, 1.0),
                      highlightColor:
                      Color.fromRGBO(50, 50, 50, 1.0),
                      child: Card(
                        elevation: 0.0, color: Color.fromRGBO(45, 45, 45, 1.0),
                        shape:
                        RoundedRectangleBorder(
                          borderRadius:
                          BorderRadius.all(Radius.circular(10),),
                        ),
                        clipBehavior:
                        Clip.antiAliasWithSaveLayer,
                        child:
                        SizedBox(
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

//  Slider image shimmer
  Widget sliderImageShimmer(width){
    return Padding(
      padding: const EdgeInsets.only(
          left:15.0,right: 15.0),
      child: new InkWell(
        onTap: () {},
        child: new Column(
          crossAxisAlignment:
          CrossAxisAlignment
              .center,
          children: [
            new Container(
              child: new Container(
                child: new ClipRRect(
                  borderRadius:
                  new BorderRadius.circular(0.0),
                  child: new SizedBox(
                    child: Shimmer
                        .fromColors(
                      baseColor: Color.fromRGBO(45, 45, 45, 1.0),
                      highlightColor: Color.fromRGBO(50, 50, 50, 1.0),
                      child: Card(
                        elevation: 0.0,
                        color: Color.fromRGBO(45, 45, 45, 1.0),
                        shape: RoundedRectangleBorder(
                          borderRadius:
                          BorderRadius
                              .all(
                            Radius.circular(
                                10),
                          ),
                        ),
                        clipBehavior:
                        Clip.antiAliasWithSaveLayer,
                        child:
                        SizedBox(
                          width: width,
                          height: 180,
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
  Widget videoImageShimmer(width){
    return SizedBox.fromSize(
        size: const Size.fromHeight(180.0),
        child: ListView.builder(
            itemCount: 10,
            scrollDirection: Axis.horizontal,
            padding: const EdgeInsets.only(
                left: 16.0, top: 4.0),
            itemBuilder: (BuildContext context,
                int position) {
              return new Padding(
                padding: const EdgeInsets.only(
                    right: 12.0),
                child: new InkWell(
                  onTap: () {},
                  child: new Column(
                    crossAxisAlignment:
                    CrossAxisAlignment
                        .center,
                    children: [
                      new Container(
                        child: new Container(
                          child: new ClipRRect(
                            borderRadius:
                            new BorderRadius.circular(8.0),
                            child: new SizedBox(
                              child: Shimmer.fromColors(
                                baseColor: Color.fromRGBO(45, 45, 45, 1.0),
                                highlightColor: Color.fromRGBO(50, 50, 50, 1.0),
                                child: Card(
                                  elevation: 0.0,
                                  color: Color.fromRGBO(45, 45, 45, 1.0),
                                  shape:
                                  RoundedRectangleBorder(
                                    borderRadius:
                                    BorderRadius
                                        .all(
                                      Radius.circular(
                                          10),
                                    ),
                                  ),
                                  clipBehavior:
                                  Clip.antiAliasWithSaveLayer,
                                  child:
                                  SizedBox(
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


// shimmer
  Widget shimmer(width){
    return SingleChildScrollView(
        child: new Column(
            key: _keyRed,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
//                  Image slider shimmer
              sliderImageShimmer(width),

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
                                top: 0.0,
                                bottom: 0.0,
                                left: 0.0,
                                right: 0.0),
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
  Widget tvSeriesAndMoviesList(){
    return Padding(
        padding: const EdgeInsets.only(
            top: 10.0, bottom: 0.0, left: 0.0, right: 0.0),
        child: ListView.builder(
            shrinkWrap: true,
            itemCount: menuDataListLength == null ? 0 : menuDataListLength,
            physics: ClampingScrollPhysics(),
            primary: true,
            scrollDirection: Axis.vertical,
            padding: const EdgeInsets.only(top: 0.0),
            itemBuilder: (BuildContext context, int index1) {
              var type;
              var description;
              var t;
              var singleId;
              List<dynamic> se;

              newVideosList1 = List<VideoDataModel>.generate(
                  menuDataArray[index1].length == null ? 0 : menuDataArray[index1].length, (int index) {
                data1 = menuDataArray[index1];
                type = data1[index]['type'];
                description = data1[index]['detail'];
                t = description;
                var genreIdByM = data1[index]['genre_id'];
                singleId =  genreIdByM == null ? null : genreIdByM.split(",");

                double convrInStart;
                dynamic vRating = data1[index]['rating'];
                switch (vRating.runtimeType) {
                  case double: {
                    double tmdbrating =  data1[index]['rating'] == null ? 0 : data1[index]['rating'];
                    convrInStart = tmdbrating != null ? tmdbrating/2 : 0;
                  }
                  break;
                  case int: {
                    var tmdbrating =  data1[index]['rating'] == null ? 0 : data1[index]['rating'];
                    convrInStart = tmdbrating != null ? tmdbrating/2 : 0;
                  }
                  break;
                  case String:{
                    var tmdbrating =  data1[index]['rating'] == null ? 0 : double.parse(data1[index]['rating']);
                    convrInStart = tmdbrating != null ? tmdbrating/2 : 0;
                  }
                  break;
                }

                var idTM;
                if(type == "T"){
                  se = data1[index]['seasons'] as List<dynamic>;
                  idTM = data1[index]['comments'];
                }else{
                  se = data1[index]['movie_series'] as List<dynamic>;
                  idTM = data1[index]['comments'];
                  videoLink = data1[index]['video_link'];
                  iFrameURL =  videoLink['iframeurl'];
                  iReadyUrl = videoLink['ready_url'];
                  vUrl360 = videoLink['url_360'];
                  vUrl480 = videoLink['url_480'];
                  vUrl720 = videoLink['url_720'];
                  vUrl1080 = videoLink['url_1080'];
                }
                return VideoDataModel(
                  id: data1[index]['id'],
                  name: '${data1[index]['title']}',
                  box: type == "T"
                      ? "${APIData.tvImageUriTv}" +
                      "${data1[index]['thumbnail']}" : "${APIData.movieImageUri}" + "${data1[index]['thumbnail']}",
                  cover: type == "T" ? "${APIData.tvImageUriPosterTv}" + "${data1[index]['poster']}"
                      : "${APIData.movieImageUriPosterMovie}" +
                      "${data1[index]['poster']}",
                  description: "$t",
                  datatype: type,
                  rating: convrInStart,
                  iFrameLink: "$iFrameURL",
                  readyUrl: '$iReadyUrl',
                  videoLink: "$videoLink",
                  url360: "$vUrl360",
                  url480: "$vUrl480",
                  url720: "$vUrl720",
                  url1080: "$vUrl1080",
                  screenshots: List.generate(3, (int xyz) {
                    return type == "T"
                        ? "${APIData.tvImageUriPosterTv}" +
                        "${data1[index]['poster']}"
                        : "${APIData.movieImageUriPosterMovie}" +
                        "${data1[index]['poster']}";
                  }),
                  url: '${data1[index]['trailer_url']}',
                  menuId: 1,
                  genre: List.generate(
                      singleId == null ? 0 : singleId.length,
                          (int xyz) {
                        return "${singleId[xyz]}";
                      }),

                  genres: List.generate(
                      genreData == null ? 0 : genreData.length,
                          (int genereIndex) {
                        var genreId2 = genreData[genereIndex]['id'].toString();

                        var genrelist = List.generate(
                            singleId == null ? 0 : singleId.length,
                                (int i) {
                              return "${singleId[i]}";
                            });
                        var isAv2 = 0;
                        for (var y in genrelist) {
                          if (genreId2 == y) {
                            isAv2 = 1;
                            break;
                          }
                        }
                        if (isAv2 == 1) {
                          if( genreData[genereIndex]['name'] == null){
                          }else{
                            return "${genreData[genereIndex]['name']}";
                          }
                        }
                        return null;
                      }),

                  seasons: List<Seasons>.generate(se== null ? 0 : se.length, (int seasonIndex){
                    if(type == "T"){
                      return Seasons(
                        id: se[seasonIndex]['id'],
                        sTvSeriesId: se[seasonIndex]['tv_series_id'],
                        sSeasonNo: se[seasonIndex]['season_no'],
                        thumbnail: se[seasonIndex]['thumbnail'],
                        cover: se[seasonIndex]['poster'],
                        description: se[seasonIndex]['detail'],
                        sActorId: se[seasonIndex]['actor_id'],
                        language: se[seasonIndex]['a_language'],
                        type: se[seasonIndex]['type'],
                      );
                    } else{
                      return null;
                    }
                  }
                  ),

                  comments: List<CommentsModel>.generate(
                      idTM == null ? 0 : idTM.length, (int comIndex){
                    return CommentsModel(
                      id: idTM[comIndex]['id'],
                      cComment: idTM[comIndex]['comment'],
                      cMovieId: idTM[comIndex]['movie_id'],
                      cEmail: idTM[comIndex]['email'],
                      cName: idTM[comIndex]['name'],
                      cTvSeriesId: idTM[comIndex]['tv_series_id'],
                      cCreatedAt: idTM[comIndex]['created_at'],
                      cUpdatedAt: idTM[comIndex]['updated_at'],
                    );
                  }),
                  maturityRating: '${data1[index]['maturity_rating']}',
                  aLang: '${data1[index]['a_language']}',
                  vStatus: "${data1[index]['status']}",
                );
              }
              );

              newVideosList1.removeWhere((item) => item.vStatus == "0");

              allDataList.add(newVideosList1);

              return Padding(
                padding: const EdgeInsets.only(
                    top: 0.0,
                    bottom: 0.0,
                    left: 0.0,
                    right: 0.0),
                child: new Column(
                  children: <Widget>[
                    Padding(padding: EdgeInsets.only(right: 15.0,),
                      child: Row(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          videoTypeContainer(type),
                          InkWell(
                            child: Text("View All",
                              style: TextStyle(
                                  fontFamily: 'Lato',
                                  fontSize: 14.0,
                                  fontWeight: FontWeight.w600,
                                  color: greenPrime),
                            ),
                            onTap: (){
                              var router = new MaterialPageRoute(
                                  builder: (BuildContext context) => GridVideosPageMt("${type == "T" ? "TV Series" : "Movies"}",'$type', newVideosList1));
                              Navigator.of(context).push(router);
                            },
                          )
                        ],
                      ),
                    ),
                    Container(
                      child: HorizontalVideoController(
                          newVideosList1.reversed.toList(),
                      ),
                    ),
                  ],
                ),
              );
            })
    );
  }

// List all genres
  Widget allGenresList(){
    return ListView.builder(
        shrinkWrap: true,
        itemCount: genreData == null ? 0 : genreData.length,
        physics: ClampingScrollPhysics(),
        primary: true,
        scrollDirection: Axis.vertical,
        padding: const EdgeInsets.only(top: 0.0),
        itemBuilder: (BuildContext context, int index1) {
          var genreName = "${genreData[index1]['name']}";

          genreId = genreData[index1]['id'].toString();
          var singleId;
          int sum = 0;
          for (var e = 0; e < menuDataListLength; e++) {
            var m2 = menuDataArray[e].length;
            sum += m2;
          }

          var gTest = List.generate(
              genreData == null ? 0 : genreData.length,
                  (int xyz) {
                var genreId2 = genreData[xyz]['id'].toString();
                var zx = List.generate(
                    singleId == null ? 0 : singleId.length,
                        (int xyz) {
                      return "${singleId[xyz]}";
                    }
                );
                var isAv2 = 0;
                for (var y in zx) {
                  if (genreId2 == y) {
                    isAv2 = 1;
                    break;
                  }
                }
                if (isAv2 == 1) {
                  if( genreData[xyz]['name'] == null){
                  }
                  else{
                    return "${genreData[xyz]['name']}";
                  }
                }
                return null;
              }
          );

          gTest.removeWhere((value) => value == null);
          newVideosListG = List<VideoDataModel>.generate(
              searchIds == null ? 0 : sum, (int index) {
            var type = searchIds[index]['type'];
            var description =
            searchIds[index]['detail'];
            var t = description;
            var genreIdByM = searchIds[index]['genre_id'];
            singleId = genreIdByM == null ? null : genreIdByM.split(",");

            double convrInStart;
            dynamic vRating = searchIds[index]['rating'];
            switch (vRating.runtimeType) {
              case int:
                var tmdbrating =  searchIds[index]['rating'] == null ? 0 : searchIds[index]['rating'];
                convrInStart = tmdbrating != null ? tmdbrating / 2 : 0;
              break;
              case double:
                double tmdbrating =  searchIds2[index]['rating'] == null ? 0 : searchIds2[index]['rating'].toDouble();
                convrInStart = tmdbrating != null ? tmdbrating/2 : 0;
              break;
              case String:
                var tmdbrating =  searchIds[index]['rating'] == null ? 0 : double.parse(searchIds[index]['rating']);
                convrInStart = tmdbrating != null ? tmdbrating / 2 : 0;
              break;
            }

            List<dynamic> se;
            var comGenreLen;
            if(type == "T"){
              se = searchIds[index]['seasons'] as List<dynamic>;
              comGenreLen = searchIds[index]['comments'];
            }else{
              se = searchIds[index]['movie_series'] as List<dynamic>;
              comGenreLen = searchIds[index]['comments'];
              videoLink = searchIds[index]['video_link'];
              iFrameURL = videoLink['iframeurl'];
              iReadyUrl = videoLink['ready_url'];
              vUrl360 = videoLink['url_360'];
              vUrl480 = videoLink['url_480'];
              vUrl720 = videoLink['url_720'];
              vUrl1080 = videoLink['url_1080'];
            }
            return VideoDataModel(
              id:searchIds[index]['id'],
              name: '${searchIds[index]['title']}',
              box: type == "T"
                  ? "${APIData.tvImageUriTv}" +
                  "${searchIds[index]['thumbnail']}"
                  : "${APIData.movieImageUri}" +
                  "${searchIds[index]['thumbnail']}",
              cover: type == "T"
                  ? "${APIData.tvImageUriPosterTv}" +
                  "${searchIds[index]['poster']}"
                  : "${APIData.movieImageUriPosterMovie}" +
                  "${searchIds[index]['poster']}",
              description: '$t',
              datatype: type,
              rating: convrInStart,
              iFrameLink: "$iFrameURL",
              readyUrl: '$iReadyUrl',
              videoLink: "$videoLink",
              url360: "$vUrl360",
              url480: "$vUrl480",
              url720: "$vUrl720",
              url1080: "$vUrl1080",
              screenshots: List.generate(3, (int xyz) {
                return type == "T"
                    ? "${APIData.tvImageUriPosterTv}" +
                    "${searchIds[index]['poster']}"
                    : "${APIData.movieImageUriPosterMovie}" +
                    "${searchIds[index]['poster']}";
              }),
              url: '${searchIds[index]['trailer_url']}',
              menuId: 1,
              genre: List.generate(
                  singleId == null ? 0 : singleId.length,
                      (int xyz) {
                    return "${singleId[xyz]}";
                  }),
              genres: List.generate(
                  genreData == null ? 0 : genreData.length,
                      (int xyz) {
                    var genreId2 = genreData[xyz]['id'].toString();

                    var zx = List.generate(
                        singleId == null ? 0 : singleId.length,
                            (int xyz) {
                          return "${singleId[xyz]}";
                        });
                    var isAv2 = 0;
                    for (var y in zx) {
                      if (genreId2 == y) {
                        isAv2 = 1;
                        break;
                      }
                    }
                    if (isAv2 == 1) {
                      if( genreData[xyz]['name'] == null){

                      }else{
                        return "${genreData[xyz]['name']}";
                      }

                    }
                    return null;

                  }),

              seasons: List<Seasons>.generate(se== null ? 0 : se.length, (int s){
                if(type == "T"){
                  return new Seasons(
                    id: se[s]['id'],
                    sTvSeriesId: se[s]['tv_series_id'],
                    sSeasonNo: se[s]['season_no'],
                    thumbnail: se[s]['thumbnail'],
                    cover: se[s]['poster'],
                    description: se[s]['detail'],
                    sActorId: se[s]['actor_id'],
                    language: se[s]['a_language'],
                    type: se[s]['type'],

                  );
                }else{
                  return null;
                }
              }),
              comments: List<CommentsModel>.generate(
                  comGenreLen == null ? 0 : comGenreLen.length, (int comIndex){
                return CommentsModel(
                  id: comGenreLen[comIndex]['id'],
                  cComment: comGenreLen[comIndex]['comment'],
                  cMovieId: comGenreLen[comIndex]['movie_id'],
                  cEmail: comGenreLen[comIndex]['email'],
                  cName: comGenreLen[comIndex]['name'],
                  cTvSeriesId: comGenreLen[comIndex]['tv_series_id'],
                  cCreatedAt: comGenreLen[comIndex]['created_at'],
                  cUpdatedAt: comGenreLen[comIndex]['updated_at'],
                );
              }),
              maturityRating: '${searchIds[index]['maturity_rating']}',
              duration: type == "T" ? 'Not Available': '${searchIds[index]['duration']}',
              released: type == "T" ? 'Not Available': '${searchIds[index]['released']}',
              vStatus: "${searchIds[index]['status']}",
            );
            //   newGamesList.add(test);
          });

          newVideosListG.removeWhere((item) => item.vStatus == "0");

          var isAv = 0;
          for (var y in newVideosListG) {
            for (var x in y.genre) {
              if (genreId == x) {
                isAv = 1;
                break;
              }
            }
          }

          if (isAv == 1) {
            return new Padding(
              padding: const EdgeInsets.only(
                  top: 0.0,
                  bottom: 0.0,
                  left: 0.0,
                  right: 0.0),
              child: new Column(
                children: <Widget>[
                  new Container(
                    child:  Padding(
                      padding: const EdgeInsets.fromLTRB(
                          16.0, 12.0, 15.0, 5.0),
                      child: Row(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(
                              "$genreName",
                              style: TextStyle(
                                  fontFamily: 'Lato',
                                  fontSize: 16.0,
                                  fontWeight:
                                  FontWeight.w800,
                                  letterSpacing: 0.9,
                                  color: Colors.white),
                            ),
                            InkWell(
                              child: Text("View All",
                                style: TextStyle(
                                    fontFamily: 'Lato',
                                    fontSize: 14.0,
                                    fontWeight: FontWeight.w600,
                                    color: greenPrime),
                              ),
                              onTap: (){
                                var router = new MaterialPageRoute(
                                  builder: (BuildContext context) => GridVideosPage( genreName, genreData[index1]['id'].toString(), newVideosListG),
                                );
                                Navigator.of(context).push(router);
                              },
                            ),
                          ]),
                    ),
                  ),
                  new Container(
                      child: new HorizontalGenreController(
                          newVideosListG.reversed.toList(),
                      )
                  ),
                ],
              ),
            );
          } else {
            return new Padding(
              padding: const EdgeInsets.only(right: 0.0),
            );
          }
        }
    );
  }

// tv series and movies column
  Widget tvAndMoviesColumn(){
    return SingleChildScrollView(
        child: Column(
          key: _keyRed,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            imageSliderContainer(),

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
              child: allGenresList(),
            ),
          ],
        )
    );
  }

// slider image container
  Widget imageSliderContainer(){
    return Stack(
        children: <Widget>[
          Container(
            child: sliderData.length == 0 ?
            SizedBox.shrink()
                : ImageSlider(),
          ),
        ]);
  }
// Type Text
  Widget typeText(type){
    return Text(
      "${type == "T" ? "Latest TV Series" : "Latest Movies"}",
      style: TextStyle(
          fontFamily: 'Lato',
          fontSize: 16.0,
          fontWeight: FontWeight.w800,
          letterSpacing: 0.9,
          color: Colors.white),
    );
  }


  Widget videoTypeContainer(type){
    return Container(
      child: new Padding(
        padding: const EdgeInsets.fromLTRB(
            16.0, 12.0, 8.0, 5.0),
        child: new Row(
            mainAxisAlignment:
            MainAxisAlignment.spaceBetween,
            children: [
              typeText(type),
            ]),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    return new Container(
      child: RefreshIndicator(
        key: refreshKey,
        child:
        menuDataListLength == 0 || menuDataArray == null || menuDataArray[0] == "No Data Found"?
//      For Shimmer
          shimmer(width) :
        tvAndMoviesColumn(),
        onRefresh: refreshList,
      ),
      color: Color.fromRGBO(34, 34, 34, 1.0),
    );
  }
}






