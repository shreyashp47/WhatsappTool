import 'package:flutter/material.dart';
import 'package:pankookidz/apidata/apidata.dart';
import 'package:pankookidz/global.dart';
import 'package:pankookidz/ui/subscription.dart';
import 'package:pankookidz/utils/profile_tile.dart';


//  Container having details that is used in success dialog

class SuccessTicket extends StatelessWidget {
  SuccessTicket(this.msgResponse, this.subdate,this.name, this.time, this.index);
  final  msgResponse;
  final subdate;
  final name;
  final time;
  final index;

  @override
  Widget build(BuildContext context) {
    return WillPopScope(child: Container(
      width: double.infinity,
      padding: const EdgeInsets.all(16.0),
      child: Material(
        color: Color.fromRGBO(250, 250, 250, 1.0),
        clipBehavior: Clip.antiAlias,
        elevation: 2.0,
        borderRadius: BorderRadius.circular(4.0),
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              ProfileTile(
                title: "Thank You!",
                textColor:  Color.fromRGBO(125, 183, 91, 1.0),
                subtitle: msgResponse,
              ),
              ListTile(
                title: Text("Date",style: TextStyle(color: Colors.black)),
                subtitle: Text(subdate,style: TextStyle(color: primaryDarkColor),),
                trailing: Text(time,style: TextStyle(color: Colors.black)),
              ),
              ListTile(
                title: Text(name,style: TextStyle(color: Colors.black),),
                subtitle: Text(email,style: TextStyle(color: primaryDarkColor),),
                trailing: userImage != null ? Image.network(
                  "${APIData.profileImageUri}"+"$userImage",
                  scale: 1.7,
                  fit: BoxFit.cover,
                ):
                Image.asset(
                  "assets/avatar.png",
                  scale: 1.7,
                  fit: BoxFit.cover,
                ),
              ),
              ListTile(
                title: Text("Amount",style: TextStyle(color: Colors.black),),
                subtitle: Text("${plan_details[index]['amount']}"+" ${plan_details[index]['currency']}",style: TextStyle(color: primaryDarkColor),),
                trailing: Text("Completed",style: TextStyle(color: Colors.black),),
              ),
            ],
          ),
        ),
      ),
    ), onWillPop: ()async => false );
  }
}

class SuccessTicket2 extends StatelessWidget {
  SuccessTicket2(this.msgResponse, this.subdate, this.name, this.time, this.currency, this.planAmount);
  final  msgResponse;
  final subdate;
  final name;
  final time;
  final currency;
  final planAmount;

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      child: Container(
        width: double.infinity,
        padding: const EdgeInsets.all(16.0),
        child: Material(
          color: Color.fromRGBO(250, 250, 250, 1.0),
          clipBehavior: Clip.antiAlias,
          elevation: 2.0,
          borderRadius: BorderRadius.circular(4.0),
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                ProfileTile(
                  title: "Thank You!",
                  textColor:  Color.fromRGBO(125, 183, 91, 1.0),
                  subtitle: msgResponse,
                ),
                ListTile(
                  title: Text("Date",style: TextStyle(color: Colors.black)),
                  subtitle: Text(subdate,style: TextStyle(color: Color.fromRGBO(20, 20, 20, 1.0)),),
                  trailing: Text(time,style: TextStyle(color: Colors.black)),
                ),
                ListTile(
                  title: Text(name,style: TextStyle(color: Colors.black),),
                  subtitle: Text(email,style: TextStyle(color: Color.fromRGBO(20, 20, 20, 1.0)),),
                  trailing: userImage != null ? Image.network(
                    "${APIData.profileImageUri}"+"$userImage",
                    scale: 1.7,
                    fit: BoxFit.cover,
                  ):
                  Image.asset(
                    "assets/avatar.png",
                    scale: 1.7,
                    fit: BoxFit.cover,
                  ),
                ),
                ListTile(
                  title: Text("Amount",style: TextStyle(color: Colors.black),),
                  subtitle: Text("$planAmount"+" $currency",style: TextStyle(color: Color.fromRGBO(20, 20, 20, 1.0)),),
                  trailing: Text("Completed",style: TextStyle(color: Colors.black),),
                ),
              ],
            ),
          ),
        ),
      ),
      onWillPop: () async => false,
    );
  }
}