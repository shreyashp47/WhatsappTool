import 'dart:convert';
import 'dart:io';
import 'package:flutter/services.dart';
import 'package:flutter/material.dart';
import 'package:pankookidz/apidata/apidata.dart';
import 'package:pankookidz/global.dart';
import 'package:pankookidz/loading/loading_page.dart';
import 'package:pankookidz/ui/signup.dart';
import 'package:pankookidz/widget/email_field.dart';
import 'package:pankookidz/widget/password_field.dart';
import 'package:path_provider/path_provider.dart';
import 'package:pankookidz/utils/wavy_header_image.dart';
import 'package:pankookidz/widget/reset_alert_container.dart';
import 'package:sticky_headers/sticky_headers.dart';

File jsonFile;
Directory dir;
String fileName = "userJSON.json";
bool fileExists = false;
Map<dynamic, dynamic> fileContent;
var acct;

class LoginForm extends StatefulWidget {


  @override
  LoginFormState createState() => LoginFormState();
}

class LoginFormState extends State<LoginForm> {
  String pass;
  final TextEditingController _emailController = new TextEditingController();
  final TextEditingController _passwordController = new TextEditingController();

  String msg = '';
  final scaffoldKey = new GlobalKey<ScaffoldState>();
  final formKey = new GlobalKey<FormState>();


      @override
      void initState() {
      super.initState();

      getApplicationDocumentsDirectory().then((Directory directory) {
        dir = directory;
        jsonFile = new File(dir.path + "/" + fileName);
        fileExists = jsonFile.existsSync();
        if (fileExists) {
          this.setState(
                  () => fileContent = json.decode(jsonFile.readAsStringSync()));
        }
      });
    }

//    Validate the form and and start loading home page
  void _submit() {
    final form = formKey.currentState;
    form.save();
    if (form.validate() == true) {
      startLoading();
    }
  }

//    Start loading page
      void startLoading(){
    var router = new MaterialPageRoute(
        builder: (BuildContext context) => new LoadingPage(
          isSelected : true,
          useremail : _emailController.text,
          userpass : _passwordController.text,

        ));
    Navigator.of(context).push(router);
  }

//    When user is enter wrong credentials it calls and generate login error
      void loginError() {
      Scaffold.of(context).showSnackBar(
        new SnackBar(
          backgroundColor: Colors.red,
          content: new Container(
            height: 50.0,
            child: new Column(
              children: <Widget>[
                new Text(
                  "The user credentias were incorrect..!",
                  style:
                  new TextStyle(fontSize: 20.0, fontWeight: FontWeight.w700),
                ),
              ],
            ),
          ),
        ),
      );
    }

//    When network is disconnected when logging
      void noNetwork() {
    Scaffold.of(context).showSnackBar(
      new SnackBar(
        content: new Text('Please Check Network Connection!'),
      ),
    );
  }

//    Create file to save the login email and password
      void createFile(Map<String, String> content, Directory dir, String fileName) {
    print("Creating file!");
    File file = new File(dir.path + "/" + fileName);
    file.createSync();
    setState(() {
      fileExists = true;
    });
    file.writeAsStringSync(json.encode(content));
    this.setState(() => fileContent = json.decode(jsonFile.readAsStringSync()));
  }

//    write email and password in the file
      void writeToFile(String key, String value) {
    print("Writing to file!");
    Map<String, String> content = {key: value};
    if (fileExists) {
      print("File exists");
      Map<dynamic, dynamic> jsonFileContent =
      json.decode(jsonFile.readAsStringSync());
      jsonFileContent.addAll(content);
      jsonFile.writeAsStringSync(json.encode(jsonFileContent));
    } else {
      print("File does not exist!");
      createFile(content, dir, fileName);
    }

    this.setState(() => fileContent = json.decode(jsonFile.readAsStringSync()));
//    print(fileContent);
  }

////    Tap on the radio button to remember password for future login
//    void _radio() {
//    setState(() {
//      _isSelected = !_isSelected;
//    });
//  }

//    Sign text heading
      Widget signInHeadingText(){
      return Padding(
        padding: EdgeInsets.only(left: 10.0, right: 10.0),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Text(
              "Sign In!",
              style: TextStyle(
                  color: Color.fromRGBO(34, 34, 34, 1.0),
                  fontSize: 22,
                  fontWeight: FontWeight.w800),
              textAlign: TextAlign.start,
            )
          ],
        ),
      );
    }

//    Logo image on login page
      Widget logoImage(){
      return Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Padding(
            padding: EdgeInsets.only(top: 15.0, left: 20.0),
            child: Container(
              child: homeApiResponseData == null ? new Image.asset('assets/logo.png', scale: 0.9,) : Image.network(
                '${APIData.logoImageUri}${loginConfigData['logo']}',
                scale: 0.9,
              ),
            ),
          ),
        ],
      );
    }

//    Header of StickHeader widget
      Widget stickyHeader(){
      return Stack(
        children: <Widget>[
          Container(
            margin: new EdgeInsets.fromLTRB(0.0, 50.0, 0.0, 0.0),
            child: WavyHeaderImage2(),
          ),
          Container(
            margin: new EdgeInsets.fromLTRB(0.0, 0.0, 0.0, 0.0),
            child: WavyHeaderImage(),
          ),
          logoImage(),
          IconButton(icon: Icon(Icons.arrow_back_ios, size: 24,), onPressed: (){
            Navigator.pop(context);
          }),
        ],
      );
    }

//    SignIn material button
      Widget signInMaterialButton(){
      return MaterialButton(
          height: 50.0,
          splashColor: Color.fromRGBO(125,183,91, 1.0),
          child: Text(
            "Sign In",
            style: TextStyle(color: Colors.white),
          ),
          onPressed: () {
            SystemChannels.textInput.invokeMethod('TextInput.hide');
            // ignore: unnecessary_statements
            _submit();
          }
      );
    }

////    Remember me radio button
//      Widget rememberMeRadio(){
//      return Flexible(
//        flex: 1,
//        fit: FlexFit.loose,
//        child: InkWell(
//            onTap: _radio,
//            child:  new Text("Remember me",
//                style: TextStyle(
//                  fontSize: 14,
//                  color: Colors.black87,
//                )
//            )
//        ),
//      );
//    }

//    Register here text line
      Widget registerHereText(){
      return ListTile(
          title: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Flexible(
                  flex: 1,
                  fit: FlexFit.loose,
                  child: InkWell(
                    child: new RichText(
                      text: new TextSpan(children: [
                        new TextSpan(
                          text: "If you don't have an account? ",
                          style: new TextStyle(
                              color:
                              Color.fromRGBO(34, 34, 34, 0.5),
                              fontSize: 16.5,
                              fontStyle: FontStyle.italic,
                              fontWeight: FontWeight.w600),
                        ),
                        new TextSpan(
                          text: 'Register Here. ',
                          style: new TextStyle(
                              color:
                              Color.fromRGBO(72, 163, 198, 1.0),
                              fontSize: 17.5,
                              fontStyle: FontStyle.italic,
                              fontWeight: FontWeight.bold),
                        )
                      ]),
                    ),
                    onTap: () {
                      var router = new MaterialPageRoute(
                          builder: (BuildContext context) =>
                          new SignUpForm());
                      Navigator.of(context).push(router);
                    },
                  ),
                ),
              ]));
    }

//    Email text
      Widget emailText(){
      return Padding(
        padding: EdgeInsets.only(
            left: 25.0, right: 10.0, bottom: 10.0),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Text(
              "Email",
              style: TextStyle(
                  color: Color.fromRGBO(34, 34, 34, 0.5),
                  fontSize: 18,
                  fontWeight: FontWeight.w600),
              textAlign: TextAlign.start,
            )
          ],
        ),
      );
    }

//    Password text
      Widget passwordText(){
        return Padding(
          padding: EdgeInsets.only(
              left: 25.0, right: 10.0, bottom: 10.0),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text(
                "Password",
                style: TextStyle(
                    color: Color.fromRGBO(34, 34, 34, 0.5),
                    fontSize: 18,
                    fontWeight: FontWeight.w600),
                textAlign: TextAlign.start,
              ),
            ],
          ),
        );
    }

//    SignIn button container
      Widget signInButtonContainer(){
      return Expanded(
        flex: 2,
        child: InkWell(
          child: Material(
            borderRadius: BorderRadius.circular(5.0),
            child: Container(
              height: 50.0,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(5.0),
                gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomRight,
                  // Add one stop for each color. Stops should increase from 0 to 1
                  stops: [0.1, 0.5, 0.7, 0.9],
                  colors: [
                    // Colors are easy thanks to Flutter's Colors class.
                    Color.fromRGBO(
                        72, 163, 198, 0.4)
                        .withOpacity(0.4),
                    Color.fromRGBO(
                        72, 163, 198, 0.3)
                        .withOpacity(0.5),
                    Color.fromRGBO(
                        72, 163, 198, 0.2)
                        .withOpacity(0.6),
                    Color.fromRGBO(
                        72, 163, 198, 0.1)
                        .withOpacity(0.7),
                  ],
                ),
                boxShadow: <BoxShadow>[
                  new BoxShadow(
                    color: Colors.black.withOpacity(0.20),
                    blurRadius: 10.0,
                    offset: new Offset(1.0, 10.0),
                  ),
                ],
              ),

              child: signInMaterialButton(),
            ),
          ),
        ),
      );
    }

//    Row is sub widget of ListTile that contains remember me radio button and sign in button.
      Widget signInButtonListRow(){
      return ListTile(
          title: Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
//              GestureDetector(
//                onTap: _radio,
//                child: RadioButtonField(_isSelected),
//              ),
//              SizedBox(
//                width: 8.0,
//              ),
//              rememberMeRadio(),
//              SizedBox(
//                width: 10.0,
//              ),
              signInButtonContainer(),
            ],
          ));
      }

//    Content of StickyHeader widget
      Widget content(){
        return Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            signInHeadingText(),
            SizedBox(
              height: 30.0,
            ),
            new Container(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  SizedBox(
                    height: 10.0,
                  ),
                  emailText(),
                  Padding(
                    padding:
                    EdgeInsets.fromLTRB(10.0, 0.0, 10.0, 0.0),
                    child: EmailField(_emailController),
                  ),
                  SizedBox(
                    height: 20.0,
                  ),
                  passwordText(),
                  Padding(
                    padding:
                    EdgeInsets.fromLTRB(10.0, 0.0, 10.0, 0.0),
                    child: HiddenPasswordField(_passwordController,"Enter your password"),
                  ),
                  SizedBox(
                    height: 15.0,
                  ),
                  forgotPasswordField(),
                  SizedBox(
                    height: 5.0,
                  ),
                  signInButtonListRow(),
                  SizedBox(
                    height: 15.0,
                  ),
                  registerHereText(),
                  SizedBox(
                    height: 30.0,
                  ),
                ],
              ),
            ),
          ],
      );
    }

//    Container under login form
      Widget loginFormContainer(){
      return Container(
        color: Colors.white.withOpacity(0.95),
        alignment: Alignment.center,
        child: Center(
          child: new ListView(
            children: <Widget>[
              StickyHeader(
                header: stickyHeader(),
                content: content(),
              ),
            ],
          ),
        ),
      );
    }

//    Forgot password field
      Widget forgotPasswordField(){
        return ListTile(
          trailing: Padding(padding: EdgeInsets.only(left: 15.0),
            child: Text(
              'Forgot Password? ',
              style: new TextStyle(
                color: Colors.black87,
                fontSize: 14.0,),
            ),),
          onTap: resetPasswordAlertBox,
        );
  }

      resetPasswordAlertBox() {
        return showDialog(
            context: context,
            builder: (BuildContext context) {
              return AlertDialog(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.all(Radius.circular(20.0))),
                contentPadding: EdgeInsets.only(top: 10.0),
                content: ResetAlertBoxContainer(),
              );
            });
      }


//    Login form
      Widget form(){
      return Form(
        onWillPop: () async {
          return true;
        },
        key: formKey,
        child: loginFormContainer(),
      );
    }

      @override
      Widget build(BuildContext context) {
      return SafeArea(
        child: form(),
      );
    }
}
