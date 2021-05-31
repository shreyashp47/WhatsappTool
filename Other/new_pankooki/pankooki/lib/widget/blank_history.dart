import 'package:flutter/material.dart';
import 'package:pankookidz/global.dart';

//  When history is not available then this widget will called
class BlankHistoryContainer extends StatelessWidget {

//  Blank history icon
  Widget blankHistoryIcon(){
    return Container(
      child: Icon(Icons.history, size: 150.0, color: Color.fromRGBO(70, 70, 70, 1.0),),
    );
  }
//  Blank history message
  Widget blankHistoryMessage(){
    return Padding(padding: EdgeInsets.only(left: 50.0, right: 50.0),
      child: Text("No History Available.",
        style: TextStyle(height: 1.5,color: Colors.white70),),);
  }
  Widget appBar() {
    return AppBar(
      title: Text("Payment History", style: TextStyle(fontSize: 16.0),),
      centerTitle: true,
      backgroundColor: primaryDarkColor,
    );
  }

//  Blank history column
  Widget blankHistoryColumn(){
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        blankHistoryIcon(),
        SizedBox(
          height: 25.0,
        ),
        blankHistoryMessage(),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appBar(),
      body: Container(
        alignment: Alignment.center,
        child: blankHistoryColumn(),
      ),
    );
  }
}
