import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

class PankooStore extends StatefulWidget {
  @override
  _PankooStoreState createState() => new _PankooStoreState();
}

class _PankooStoreState extends State<PankooStore> {
  @override
  void initState() {
    // this.shopRedirect();
    super.initState();
  }

  shopRedirect() async {
    const url = 'https://amazon.in/pankookidz ';

    if (await canLaunch(url)) {
      await launch(url, forceSafariVC: false);
    } else {
      throw 'Could not launch $url';
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("assets/logo.png"),
            fit: BoxFit.fitWidth,
          ),
        ),
        child: Center(
            child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.fromLTRB(10, 40, 0, 10),
              child: Text(
                "Pankoo Kidz Merchandise!",
                style: TextStyle(color: Colors.white, fontSize: 30),
              ),
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(10, 40, 0, 10),
              child: Text(
                "WELCOME TO OUR INTERNATIONAL STORE",
                style: TextStyle(color: Colors.white, fontSize: 15),
              ),
            ),
            RaisedButton.icon(
              icon: Icon(Icons.shop),
              label: Text(" SHOP NOW "),
              onPressed: shopRedirect,
              color: Colors.black38,
              textColor: Colors.white,
              splashColor: Colors.grey,
              padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
            ),
          ],
        )),
      ),
    );
  }
}
