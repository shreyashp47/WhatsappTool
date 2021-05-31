import 'dart:io';

import 'package:firebase_admob/firebase_admob.dart';

class AdMobService {
  String getAdMobId() {
    if (Platform.isIOS) {
      return '';
    } else if (Platform.isAndroid) {
     ///test
      // / return 'ca-app-pub-3940256099942544~3347511713';
       return 'ca-app-pub-8775900896857481~7717506010';

    }
    return null;
  }

  String getAdMobInterstitialId() {
    if (Platform.isIOS) {
      return '';
    } else if (Platform.isAndroid) {
      //test
      //return 'ca-app-pub-3940256099942544/1033173712';
      //live
       return 'ca-app-pub-8775900896857481/8897165247';
    }
    return null;
  }


  InterstitialAd getNewInterstitial() {
    return InterstitialAd(
      adUnitId: getAdMobInterstitialId(),
      targetingInfo: targetingInfo,
      listener: (MobileAdEvent event) {
        print("InterstitialAd event is $event");
      },
    );
  }

  static const MobileAdTargetingInfo targetingInfo = MobileAdTargetingInfo(
     keywords: <String>['baby', 'kid'],
    childDirected: true,
    nonPersonalizedAds: true,
  );

}
