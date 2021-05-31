package com.taxivaxi.spoc.BookingForm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.taxivaxi.spoc.Constant;
import com.taxivaxi.spoc.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailedEmployeeInfo extends AppCompatActivity {

    EditText emphno1,emphno2,emphno3,emphno4,emphno5,emphno6;
    SearchableSpinner empid1,empid2,empid3,empid4,empid5,empid6;
    static ArrayAdapter<String> arrayAdapter;
    SharedPreferences employeepref;
    static ArrayList<String> idlist,phnolist,genderlist,agelist,id_type_list,id_proof_nolist;
    static TextView emp1,emp2,emp3,emp4,emp5,emp6;
    static String id_type1,id_no1="#",ag1="#";
    static String id_type2,id_no2="#",ag2="#";
    static String id_type3,id_no3="#",ag3="#";
    static String id_type4,id_no4="#",ag4="#";
    static String id_type5,id_no5="#",ag5="#";
    static String id_type6,id_no6="#",ag6="#";

    static String male_female1 = "Male",selected_emp1,phone1,value1;
    static String male_female2 = "Male",selected_emp2,phone2,value2;
    static String male_female3 = "Male",selected_emp3,phone3,value3;
    static String male_female4 = "Male",selected_emp4,phone4,value4;
    static String male_female5 = "Male",selected_emp5,phone5,value5;
    static String male_female6 = "Male",selected_emp6,phone6,value6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_employee_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enter Employee Details");
        LinearLayout l1,l2,l3,l4,l5,l6;
        l1=(LinearLayout)findViewById(R.id.employee_lay1);
        l2=(LinearLayout)findViewById(R.id.employee_lay2);
        l3=(LinearLayout)findViewById(R.id.employee_lay3);
        l4=(LinearLayout)findViewById(R.id.employee_lay4);
        l5=(LinearLayout)findViewById(R.id.employee_lay5);
        l6=(LinearLayout)findViewById(R.id.employee_lay6);

        SharedPreferences loginpref=getSharedPreferences("login_info",MODE_PRIVATE);
        final Intent intent=getIntent();

        final String noofpassenger=intent.getStringExtra("no_of_employee");
        Log.e("no",noofpassenger);
        switch (noofpassenger){
            case "1":
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                l6.setVisibility(View.GONE);
                break;
            case "2":   l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                l6.setVisibility(View.GONE);
                break;
            case "3":       l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                l6.setVisibility(View.GONE);
                break;
            case "4":
                l5.setVisibility(View.GONE);
                l6.setVisibility(View.GONE);
                break;
            case "5":
                l6.setVisibility(View.GONE);
                break;
        }
        emp1 = (TextView) findViewById(R.id.basicDetails1);
        emp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment basicDetailFragment = new BasicDetailFragment();
                Bundle bundle=new Bundle();
                bundle.putString("emp","emp1");
                basicDetailFragment.setArguments(bundle);
                basicDetailFragment.show(getSupportFragmentManager(), "emp1");

            }
        });
        emp2 = (TextView) findViewById(R.id.basicDetails2);

        emp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment basicDetailFragment = new BasicDetailFragment();
                Bundle bundle=new Bundle();
                bundle.putString("emp","emp2");
                basicDetailFragment.setArguments(bundle);
                basicDetailFragment.show(getSupportFragmentManager(), "emp2");

            }
        });
        emp3= (TextView) findViewById(R.id.basicDetails3);

        emp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment basicDetailFragment = new BasicDetailFragment();
                Bundle bundle=new Bundle();
                bundle.putString("emp","emp3");
                basicDetailFragment.setArguments(bundle);
                basicDetailFragment.show(getSupportFragmentManager(), "emp3");

            }
        });
        emp4 = (TextView) findViewById(R.id.basicDetails4);

        emp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment basicDetailFragment = new BasicDetailFragment();
                Bundle bundle=new Bundle();
                bundle.putString("emp","emp4");
                basicDetailFragment.setArguments(bundle);
                basicDetailFragment.show(getSupportFragmentManager(), "emp4");

            }
        });
        emp5 = (TextView) findViewById(R.id.basicDetails5);

        emp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment basicDetailFragment = new BasicDetailFragment();
                Bundle bundle=new Bundle();
                bundle.putString("emp","emp5");
                basicDetailFragment.setArguments(bundle);
                basicDetailFragment.show(getSupportFragmentManager(), "emp5");

            }
        });
        emp6= (TextView) findViewById(R.id.basicDetails6);

        emp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment basicDetailFragment = new BasicDetailFragment();
                Bundle bundle=new Bundle();
                bundle.putString("emp","emp6");
                basicDetailFragment.setArguments(bundle);
                basicDetailFragment.show(getSupportFragmentManager(), "emp6");

            }
        });

        idlist=new ArrayList<>();
        phnolist=new ArrayList<>();
        genderlist=new ArrayList<>();
        agelist=new ArrayList<>();
        id_type_list=new ArrayList<>();
        id_proof_nolist=new ArrayList<>();
        employeepref=getSharedPreferences("employeeinfo",MODE_PRIVATE);

        if(employeepref.getString("employeeinfo","n").equals("n")){
            if(isInternetConnected()) {

                list1.add(new String("Employee ID"));
                idlist.add(new String("Employee ID"));
                phnolist.add(new String(""));

                genderlist.add(new String(""));
                agelist.add(new String(""));
                id_type_list.add(new String(""));
                id_proof_nolist.add(new String(""));

                arrayAdapter = new ArrayAdapter<String>(this, R.layout.no_of_passenger_spinner_layout, list1);
              /*  empid1.setAdapter(arrayAdapter);
                empid2.setAdapter(arrayAdapter);
                empid3.setAdapter(arrayAdapter);
                empid4.setAdapter(arrayAdapter);
                empid5.setAdapter(arrayAdapter);
                empid6.setAdapter(arrayAdapter);*/
                new getAllEmployee().execute(loginpref.getString("token", "n"));
            }
            else {
                dialog=new AlertDialog.Builder(DetailedEmployeeInfo.this);
                dialog.setMessage("INTERNET CONNECTION UNAVAILABLE");
                dialog.show();
                finish();
            }
        }
        else {
            Log.e("json",employeepref.getString("employeeinfo","n"));
            JSONObject jsonObject=(JSONObject) JSONValue.parse(employeepref.getString("employeeinfo","n"));
            JSONObject response=(JSONObject)jsonObject.get("response");
            Log.e("response",response.toString());
            JSONArray array=(JSONArray)response.get("People");
            Iterator iterator = array.iterator();
            list1.add(new String("Employee ID"));
            idlist.add(new String("Employee ID"));
            phnolist.add(new String(""));

            genderlist.add(new String(""));
            agelist.add(new String(""));
            id_type_list.add(new String(""));
            id_proof_nolist.add(new String(""));

            while(iterator.hasNext())
            {
                try {
                    JSONObject object=(JSONObject)iterator.next();
                    list1.add(new String(object.get("people_name").toString()+"/"+object.get("people_cid").toString()));
                    idlist.add(new String(object.get("id").toString()));
                    phnolist.add(new String(object.get("people_contact").toString()));

                    genderlist.add(new String(object.get("gender").toString()));
                    agelist.add(new String(object.get("age").toString()));
                    id_type_list.add(new String(object.get("id_proof_type").toString()));
                    id_proof_nolist.add(new String(object.get("id_proof_no").toString()));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }


            arrayAdapter=new ArrayAdapter<String>(this,R.layout.no_of_passenger_spinner_layout,list1);
          /*  empid1.setAdapter(arrayAdapter);
            empid2.setAdapter(arrayAdapter);
            empid3.setAdapter(arrayAdapter);
            empid4.setAdapter(arrayAdapter);
            empid5.setAdapter(arrayAdapter);
            empid6.setAdapter(arrayAdapter);*/
        }

        Button employeebutton=(Button)findViewById(R.id.employeesubmit);
        employeebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("info",emp1.getText().toString().length()+"   a");

                ArrayList<String> employeeid,employeephno,employeeidvalue,age_list,male_female_list,id_type_list,id_no_list;
                employeeid=new ArrayList<String>();
                employeeidvalue=new ArrayList<String>();
                employeephno=new ArrayList<String>();
                age_list=new ArrayList<String>();
                male_female_list=new ArrayList<String>();
                id_type_list=new ArrayList<String>();
                id_no_list=new ArrayList<String>();


                switch(noofpassenger){
                    case "1":   if(emp1.getText().toString().length()>1){
                        employeeid.add(selected_emp1);
                        employeephno.add(phone1);
                        employeeidvalue.add(value1);
                        age_list.add(ag1);
                        male_female_list.add(male_female1);
                        id_type_list.add(id_type1);
                        id_no_list.add(id_no1);

                        Log.e("idvalue",value1);
                        intent.putStringArrayListExtra("employee_id",employeeid);
                        intent.putStringArrayListExtra("employee_phno",employeephno);
                        intent.putStringArrayListExtra("employeeIdValue",employeeidvalue);
                        intent.putStringArrayListExtra("age",age_list);
                        intent.putStringArrayListExtra("male_female",male_female_list);
                        intent.putStringArrayListExtra("id_type",id_type_list);
                        intent.putStringArrayListExtra("id_no",id_no_list);



                        setResult(RESULT_OK,intent);
                        finish();}
                    else{
                        Toast.makeText(DetailedEmployeeInfo.this,"Please fill Employee info",Toast.LENGTH_SHORT).show();
                    }
                        break;
                    case "2":   if(emp1.getText().length()>1&&emp2.getText().length()>1){
                        employeeid.add(selected_emp1);
                        employeephno.add(phone1);
                        employeeidvalue.add(value1);
                        age_list.add(ag1);
                        male_female_list.add(male_female1);
                        id_type_list.add(id_type1);
                        id_no_list.add(id_no1);
                        Log.e("idvalue",value1);
                        employeeid.add(selected_emp2);
                        employeephno.add(phone2);
                        employeeidvalue.add(value2);
                        age_list.add(ag2);
                        male_female_list.add(male_female2);
                        id_type_list.add(id_type2);
                        id_no_list.add(id_no2);
                        Log.e("idvalue",value2);
                        intent.putStringArrayListExtra("employee_id",employeeid);
                        intent.putStringArrayListExtra("employee_phno",employeephno);
                        intent.putStringArrayListExtra("employeeIdValue",employeeidvalue);
                        intent.putStringArrayListExtra("age",age_list);
                        intent.putStringArrayListExtra("male_female",male_female_list);
                        intent.putStringArrayListExtra("id_type",id_type_list);
                        intent.putStringArrayListExtra("id_no",id_no_list);
                        setResult(RESULT_OK,intent);
                        finish();}
                    else{
                        Toast.makeText(DetailedEmployeeInfo.this,"Please fill Employee info",Toast.LENGTH_SHORT).show();
                    }
                        break;
                    case "3":   if(emp1.getText().length()>1&&emp2.getText().length()>1&&emp3.getText().length()>1){
                        employeeid.add(selected_emp1);
                        employeephno.add(phone1);
                        employeeidvalue.add(value1);
                        age_list.add(ag1);
                        male_female_list.add(male_female1);
                        id_type_list.add(id_type1);
                        id_no_list.add(id_no1);
                        Log.e("idvalue",value1);
                        employeeid.add(selected_emp2);
                        employeephno.add(phone2);
                        employeeidvalue.add(value2);
                        age_list.add(ag2);
                        male_female_list.add(male_female2);
                        id_type_list.add(id_type2);
                        id_no_list.add(id_no2);
                        Log.e("idvalue",value2);
                        employeeid.add(selected_emp3);
                        employeephno.add(phone3);
                        employeeidvalue.add(value3);
                        age_list.add(ag3);
                        male_female_list.add(male_female3);
                        id_type_list.add(id_type3);
                        id_no_list.add(id_no3);
                        Log.e("idvalue",value3);
                        intent.putStringArrayListExtra("employee_id",employeeid);
                        intent.putStringArrayListExtra("employee_phno",employeephno);
                        intent.putStringArrayListExtra("employeeIdValue",employeeidvalue);
                        intent.putStringArrayListExtra("age",age_list);
                        intent.putStringArrayListExtra("male_female",male_female_list);
                        intent.putStringArrayListExtra("id_type",id_type_list);
                        intent.putStringArrayListExtra("id_no",id_no_list);
                        setResult(RESULT_OK,intent);
                        finish();}
                    else{
                        Toast.makeText(DetailedEmployeeInfo.this,"Please fill Employee info",Toast.LENGTH_SHORT).show();
                    }
                        break;
                    case "4":   if(emp1.getText().length()>1&&emp2.getText().length()>1&&
                           emp3.getText().length()>1&&emp4.getText().length()>1){
                        employeeid.add(selected_emp1);
                        employeephno.add(phone1);
                        employeeidvalue.add(value1);
                        age_list.add(ag1);
                        male_female_list.add(male_female1);
                        id_type_list.add(id_type1);
                        id_no_list.add(id_no1);
                        Log.e("idvalue",value1);
                        employeeid.add(selected_emp2);
                        employeephno.add(phone2);
                        employeeidvalue.add(value2);
                        age_list.add(ag2);
                        male_female_list.add(male_female2);
                        id_type_list.add(id_type2);
                        id_no_list.add(id_no2);
                        Log.e("idvalue",value2);
                        employeeid.add(selected_emp3);
                        employeephno.add(phone3);
                        employeeidvalue.add(value3);
                        age_list.add(ag3);
                        male_female_list.add(male_female3);
                        id_type_list.add(id_type3);
                        id_no_list.add(id_no3);
                        Log.e("idvalue",value3);
                        employeeid.add(selected_emp4);
                        employeephno.add(phone4);
                        employeeidvalue.add(value4);
                        age_list.add(ag4);
                        male_female_list.add(male_female4);
                        id_type_list.add(id_type4);
                        id_no_list.add(id_no4);
                        Log.e("idvalue",value4);
                        intent.putStringArrayListExtra("employee_id",employeeid);
                        intent.putStringArrayListExtra("employee_phno",employeephno);
                        intent.putStringArrayListExtra("employeeIdValue",employeeidvalue);
                        intent.putStringArrayListExtra("age",age_list);
                        intent.putStringArrayListExtra("male_female",male_female_list);
                        intent.putStringArrayListExtra("id_type",id_type_list);
                        intent.putStringArrayListExtra("id_no",id_no_list);
                        setResult(RESULT_OK,intent);
                        finish();}
                    else{
                        Toast.makeText(DetailedEmployeeInfo.this,"Please fill Employee info",Toast.LENGTH_SHORT).show();
                    }
                        break;
                    case "5":   if(emp1.getText().length()>1&&emp2.getText().length()>1&&
                            emp3.getText().length()>1&&emp4.getText().length()>1&&emp6.getText().length()>1){
                        employeeid.add(selected_emp1);
                        employeephno.add(phone1);
                        employeeidvalue.add(value1);
                        age_list.add(ag1);
                        male_female_list.add(male_female1);
                        id_type_list.add(id_type1);
                        id_no_list.add(id_no1);
                        Log.e("idvalue",value1);
                        employeeid.add(selected_emp2);
                        employeephno.add(phone2);
                        employeeidvalue.add(value2);
                        age_list.add(ag2);
                        male_female_list.add(male_female2);
                        id_type_list.add(id_type2);
                        id_no_list.add(id_no2);
                        Log.e("idvalue",value2);
                        employeeid.add(selected_emp3);
                        employeephno.add(phone3);
                        employeeidvalue.add(value3);
                        age_list.add(ag3);
                        male_female_list.add(male_female3);
                        id_type_list.add(id_type3);
                        id_no_list.add(id_no3);
                        Log.e("idvalue",value3);
                        employeeid.add(selected_emp4);
                        employeephno.add(phone4);
                        employeeidvalue.add(value4);
                        age_list.add(ag4);
                        male_female_list.add(male_female4);
                        id_type_list.add(id_type4);
                        id_no_list.add(id_no4);
                        Log.e("idvalue",value4);
                        employeeid.add(selected_emp5);
                        employeephno.add(phone5);
                        employeeidvalue.add(value5);
                        age_list.add(ag5);
                        male_female_list.add(male_female5);
                        id_type_list.add(id_type5);
                        id_no_list.add(id_no5);
                        Log.e("idvalue",value5);
                        intent.putStringArrayListExtra("employee_id",employeeid);
                        intent.putStringArrayListExtra("employee_phno",employeephno);
                        intent.putStringArrayListExtra("employeeIdValue",employeeidvalue);
                        intent.putStringArrayListExtra("age",age_list);
                        intent.putStringArrayListExtra("male_female",male_female_list);
                        intent.putStringArrayListExtra("id_type",id_type_list);
                        intent.putStringArrayListExtra("id_no",id_no_list);
                        setResult(RESULT_OK,intent);
                        finish();}
                    else{
                        Toast.makeText(DetailedEmployeeInfo.this,"Please fill Employee info",Toast.LENGTH_SHORT).show();
                    }
                        break;
                    case "6":   if(emp1.getText().length()>1&&emp2.getText().length()>1
                        &&emp3.getText().length()>1&&emp4.getText().length()>1&&emp5.getText().length()>1&&emp6.getText().length()>1){
                        employeeid.add(selected_emp1);
                        employeephno.add(phone1);
                        employeeidvalue.add(value1);
                        age_list.add(ag1);
                        male_female_list.add(male_female1);
                        id_type_list.add(id_type1);
                        id_no_list.add(id_no1);
                        Log.e("idvalue",value1);
                        employeeid.add(selected_emp2);
                        employeephno.add(phone2);
                        employeeidvalue.add(value2);
                        age_list.add(ag2);
                        male_female_list.add(male_female2);
                        id_type_list.add(id_type2);
                        id_no_list.add(id_no2);
                        Log.e("idvalue",value2);
                        employeeid.add(selected_emp3);
                        employeephno.add(phone3);
                        employeeidvalue.add(value3);
                        age_list.add(ag3);
                        male_female_list.add(male_female3);
                        id_type_list.add(id_type3);
                        id_no_list.add(id_no3);
                        Log.e("idvalue",value3);
                        employeeid.add(selected_emp4);
                        employeephno.add(phone4);
                        employeeidvalue.add(value4);
                        age_list.add(ag4);
                        male_female_list.add(male_female4);
                        id_type_list.add(id_type4);
                        id_no_list.add(id_no4);
                        Log.e("idvalue",value4);
                        employeeid.add(selected_emp5);
                        employeephno.add(phone5);
                        employeeidvalue.add(value5);
                        age_list.add(ag5);
                        male_female_list.add(male_female5);
                        id_type_list.add(id_type5);
                        id_no_list.add(id_no5);
                        Log.e("idvalue",value5);
                        employeeid.add(selected_emp6);
                        employeephno.add(phone6);
                        employeeidvalue.add(value6);
                        age_list.add(ag6);
                        male_female_list.add(male_female6);
                        id_type_list.add(id_type6);
                        id_no_list.add(id_no6);
                        Log.e("idvalue",value6);
                        intent.putStringArrayListExtra("employee_id",employeeid);
                        intent.putStringArrayListExtra("employee_phno",employeephno);
                        intent.putStringArrayListExtra("employeeIdValue",employeeidvalue);
                        intent.putStringArrayListExtra("age",age_list);
                        intent.putStringArrayListExtra("male_female",male_female_list);
                        intent.putStringArrayListExtra("id_type",id_type_list);
                        intent.putStringArrayListExtra("id_no",id_no_list);
                        setResult(RESULT_OK,intent);
                        finish();}
                    else{
                        Toast.makeText(DetailedEmployeeInfo.this,"Please fill Employee info",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    AlertDialog.Builder dialog;
    public boolean isInternetConnected(){
        ConnectivityManager cm=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo[]=cm.getAllNetworkInfo();

        int i;
        for(i=0;i<networkInfo.length;++i){
            if(networkInfo[i].getState()== NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;

    }
    public static class BasicDetailFragment extends DialogFragment {
        private ImageView male,female;
        private TextView total_text;
        EditText card_no,age;
        Spinner type;
        Button ok;
        Boolean check=true;
        boolean selcted =false;
        RelativeLayout relativeLayout;
        String id;
        SearchableSpinner employeeSpinner;
        EditText phone;
        String defualt_gender="Male";
        public BasicDetailFragment(){

        }

        public static BasicDetailFragment newInstance(int no){
            BasicDetailFragment frag = new BasicDetailFragment();
            Bundle args = new Bundle();
            args.putInt("details",no);
            frag.setArguments(args);
            return  frag;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
            id=new String();
            id=getArguments().getString("emp");
            return inflater.inflate(R.layout.bus_passenger_details,container);
        }

        @Override

        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

            super.onViewCreated(view, savedInstanceState);
            employeeSpinner = (SearchableSpinner) view.findViewById(R.id.employee_spinner);
            phone  = (EditText) view.findViewById(R.id.phone_no);
            male = (ImageView) view.findViewById(R.id.male);
            female = (ImageView) view.findViewById(R.id.female);
            card_no = (EditText) view.findViewById(R.id.card_no);
            age= (EditText)view.findViewById(R.id.age);
            type =(Spinner) view.findViewById(R.id.id_type);
            final String idproofs[]={"ID Type","Aadhaar Card","Voter ID","Driving License","PAN Card"};
            ok = (Button) view.findViewById(R.id.ok);
            ArrayAdapter idadapter=new ArrayAdapter<String>(getActivity(),R.layout.no_of_passenger_spinner_layout,idproofs);
            type.setAdapter(idadapter);
            male.setSelected(true);

            if(check){
                male.setSelected(true);
                female.setSelected(false);
            }else{
                female.setSelected(true);
                male.setSelected(false);
            }
            male.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    male.setSelected(true);
                    female.setSelected(false);
                    defualt_gender = "Male";




                }
            });
            female.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    female.setSelected(true);
                    male.setSelected(false);
                    defualt_gender
                            = "Female";
                    check= false;


                }
            });
            employeeSpinner.setAdapter(arrayAdapter);
            employeeSpinner.setTitle("Select employee");
            employeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    phone.setText(phnolist.get(employeeSpinner.getSelectedItemPosition()));
                    try {
                        age.setText(agelist.get(employeeSpinner.getSelectedItemPosition()));
                        String t = id_type_list.get(employeeSpinner.getSelectedItemPosition());
                        type.setSelection(Arrays.asList(idproofs).indexOf(t));
                        card_no.setText(id_proof_nolist.get(employeeSpinner.getSelectedItemPosition()));
                        String st = genderlist.get(employeeSpinner.getSelectedItemPosition());
                        if(st.equals("Male")){
                            male.setSelected(true);
                            female.setSelected(false);
                            defualt_gender = "Male";
                        }else if(st.equals("Male")){
                            female.setSelected(true);
                            male.setSelected(false);
                            defualt_gender
                                    = "Female";
                            check= false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(age.getText().toString().length()<1 ||
                            card_no.getText().toString().length()<1||
                            type.getSelectedItem().equals("ID Type")||
                            employeeSpinner.getSelectedItem().equals("Employee ID")||
                            phone.getText().length()>10){

                        if(age.getText().toString().length()<1){
                            age.setError("field required");

                        }if(card_no.getText().toString().length()<1) {
                            card_no.setError("field required");
                            // ag = age.getText().toString();
                        }if(type.getSelectedItem().equals("ID Type")){
                            TextView errorText = (TextView)type.getSelectedView();
                            errorText.setError("field required");
                            errorText.setTextColor(Color.RED);
                            errorText.setError(null);

                        }if(employeeSpinner.getSelectedItem().equals("Employee ID")){
                            TextView errorText = (TextView)type.getSelectedView();
                            errorText.setError("field required");
                            errorText.setTextColor(Color.RED);
                            errorText.setError(null);
                        }if(phone.getText().length()>10){
                            phone.setError("field required");
                        }

                    }
                    else{

                        if(id.equals("emp1")) {
                            ag1= age.getText().toString();
                            id_type1 = type.getSelectedItem().toString();
                            selected_emp1=employeeSpinner.getSelectedItem().toString();
                            String[] sp = selected_emp1.split("/");
                            Log.d("splited text",sp[0]);
                            selected_emp1 = sp[0];
                            value1 = idlist.get(employeeSpinner.getSelectedItemPosition());
                            phone1=phone.getText().toString();
                            id_no1 = card_no.getText().toString();
                            male_female1= defualt_gender;
                            emp1.setText(selected_emp1+"| "+male_female1 + "| " + ag1 + "|" + id_no1);
                            Log.d("basic",male_female1+" "+ag1+" "+id_no1);

                        }else if(id.equals("emp2")){
                            ag2= age.getText().toString();
                            id_type2 = type.getSelectedItem().toString();
                            id_no2 = card_no.getText().toString();
                            male_female2= defualt_gender;
                            selected_emp2=employeeSpinner.getSelectedItem().toString();
                            String[] sp = selected_emp2.split("/");
                            Log.d("splited text",sp[0]);
                            selected_emp2 = sp[0];
                            value2 = idlist.get(employeeSpinner.getSelectedItemPosition());
                            phone2=phone.getText().toString();
                            emp2.setText(selected_emp2+"| "+male_female2 + "| " + ag2 + "|" + id_no2);
                            Log.d("basic",male_female2+" "+ag2+" "+id_no2);

                        }else if(id.equals("emp3")){
                            ag3= age.getText().toString();
                            id_type3 = type.getSelectedItem().toString();
                            id_no3 = card_no.getText().toString();
                            male_female3= defualt_gender;
                            selected_emp3=employeeSpinner.getSelectedItem().toString();
                            value3= idlist.get(employeeSpinner.getSelectedItemPosition());
                            phone3=phone.getText().toString();
                            String[] sp = selected_emp3.split("/");
                            Log.d("splited text",sp[0]);
                            selected_emp3 = sp[0];
                            emp3.setText(selected_emp3+"| "+male_female3 + "| " + ag3 + "|" + id_no3);
                            Log.d("basic",male_female3+" "+ag3+" "+id_no3);


                        }else if(id.equals("emp4")){
                            ag4= age.getText().toString();
                            id_type4 = type.getSelectedItem().toString();
                            id_no4 = card_no.getText().toString();
                            male_female4= defualt_gender;
                            selected_emp4=employeeSpinner.getSelectedItem().toString();
                            value4 = idlist.get(employeeSpinner.getSelectedItemPosition());
                            phone4=phone.getText().toString();

                            String[] sp = selected_emp4.split("/");
                            Log.d("splited text",sp[0]);
                            selected_emp4 = sp[0];
                            emp4.setText(selected_emp4+"| "+male_female4 + "| " + ag4 + "|" + id_no4);
                            Log.d("basic",male_female4+" "+ag4+" "+id_no4);

                        }else if(id.equals("emp5")){
                            ag5= age.getText().toString();
                            id_type5= type.getSelectedItem().toString();
                            id_no5 = card_no.getText().toString();
                            male_female5= defualt_gender;
                            selected_emp5=employeeSpinner.getSelectedItem().toString();
                            value5 = idlist.get(employeeSpinner.getSelectedItemPosition());
                            phone5=phone.getText().toString();
                            String[] sp = selected_emp5.split("/");
                            Log.d("splited text",sp[0]);
                            selected_emp5 = sp[0];
                            emp5.setText(selected_emp5+"| "+male_female5 + "| " + ag5 + "|" + id_no5);
                            Log.d("basic",male_female5+" "+ag5+" "+id_no5);

                        }else if(id.equals("emp6")){
                            ag6= age.getText().toString();
                            id_type6 = type.getSelectedItem().toString();
                            id_no6 = card_no.getText().toString();
                            male_female6= defualt_gender;
                            selected_emp6=employeeSpinner.getSelectedItem().toString();
                            value6 = idlist.get(employeeSpinner.getSelectedItemPosition());
                            phone6=phone.getText().toString();
                            String[] sp = selected_emp6.split("/");
                            Log.d("splited text",sp[0]);
                            selected_emp6 = sp[0];
                            emp6.setText(selected_emp6+"| "+male_female6 + "| " + ag6 + "|" + id_no6);
                            Log.d("basic",male_female6+" "+ag6+" "+id_no6);

                        }
                        getDialog().dismiss();

                    }
                }
            });



        }
    }

    ArrayList<String> list1=new ArrayList<>();
    String valu;
    protected class getAllEmployee extends AsyncTask<String,Integer,String> {

        String apiURL = Constant.getallemployee;
        Integer id = 2;
        Response response;
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            RequestBody formBody = new FormBody.Builder()
                    .add("access_token", params[0])
                    .build();

            Request.Builder request = new Request.Builder();
            request.url(apiURL)
                    .post(formBody)
                    .build();
            try {
                response = client.newCall(request.build()).execute();
                valu = response.body().string();
                return valu;

            } catch (IOException e) {
                e.getMessage();
                Log.d("error", e.getMessage());
            }
            return null;

        }

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(DetailedEmployeeInfo.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            response.body().close();
            if (s == null) {
            } else {
                Log.e("employee", s);

                SharedPreferences.Editor editor = employeepref.edit();
                editor.putString("employeeinfo", s)
                        .commit();
                JSONObject json = (JSONObject) JSONValue.parse(s);
                JSONObject response = (JSONObject) json.get("response");
                JSONArray employee = (JSONArray) response.get("People");
                Iterator i = employee.iterator();
                while (i.hasNext()) {
                    try {
                        JSONObject obj = (JSONObject) i.next();
                        list1.add(new String(obj.get("people_name").toString()+"/"+obj.get("people_cid").toString()));
                        idlist.add(new String(obj.get("id").toString()));
                        phnolist.add(new String(obj.get("people_contact").toString()));
                        if(obj.get("gender")!=null){
                            genderlist.add(new String(obj.get("gender").toString()));

                        }else{
                            genderlist.add(" ");

                        }
                        if (obj.get("id_proof_type")!=null){
                            id_type_list.add(new String(obj.get("id_proof_type").toString()));
                        }else{
                            id_type_list.add(" ");

                        }
                        if(obj.get("id_proof_no")!=null){
                            id_proof_nolist.add(new String(obj.get("id_proof_no").toString()));
                        }else{
                            id_proof_nolist.add(" ");

                        }
                        if(obj.get("age")!=null){
                            agelist.add(new String(obj.get("age").toString()));
                        }else{
                            agelist.add(" ");

                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
                if (list1 != null) {

                    arrayAdapter.setNotifyOnChange(true);
                }

            }
            pd.dismiss();
        }


    }

}
