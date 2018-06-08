package events.tcs.com.events.fragment;


import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import events.tcs.com.events.R;
import events.tcs.com.events.activity.MainActivity;
import events.tcs.com.events.constant.ApplicationData;
import events.tcs.com.events.data.Card;

public class DayTwoFragment extends Fragment {
    private Activity mActivity;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set values for Card
        Card obj1 = new Card("Travel to TCS Office", "Travel to TCS Office – Gitanjali Park, complete security procedures", "09:00 AM – 10:00 AM ", "TCS", ApplicationData.gpLat, ApplicationData.gpLan, true);
        Card obj2 = new Card("Welcome address and agenda discussion", "Welcome address, visit expectations and agenda discussion\n" +
                "• Introduction with TCS Senior Management at Kolkata\n" +
                "• Meet with TCS Offshore Team Leads\n" +
                "Expectations from the Visit\n", "11:00 AM – 11:45 AM ", "TCS" +"and"+
                "Sephora", 0.0, 0.0, false);
        Card obj3=new Card("Tea/Coffee", "Tea/Coffee", "11:45 AM - 12:00 PM", "TCS", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj4=new Card("Sephora Account Review", "Sephora Account Review \n"+"• Relationship Journey\n"+"• Meet offshore Team – LVMH Floor Visit\n", "12:00 PM – 01:30 PM", "Kaushik", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj5=new Card("Lunch", "Lunch @ TCS Office \n" +
                "Personal Time/Mails etc", "01:30 PM - 03:00 PM", "", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj6=new Card("Floor-Walk", "End to End Service Delivery Experience for a Large (multi-country) Home Improvement Retailer – (Kingfisher) – SAP Transformation and AMS full services play\n", "03:00 PM – 04:00 PM", "Aniruddha Bhattacharjee\n", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj7=new Card("Tea/Coffee", "Tea/Coffee@ TCS Office", "04:00 PM – 04:15 PM", "N/A", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj8=new Card("BI Migration Project", "BI Migration Project Workshop with team", "04:15 PM  - 05:45 PM", "David and TCS team", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj9=new Card("SAP Project", "SAP AMS Workshop with team", "04:15 PM  - 05:45 PM", "Stephane and TCS team", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj10=new Card("Wrap", "Wrap-up for the day", "05:45 PM – 06:00 PM", "N/A", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj11=new Card("Travel to Hotel", "INFORMAL DINNER – SPECIALIZED LOCAL FOOD", "06:00 PM – 06:30 PM", "TCS", ApplicationData.hotelLat, ApplicationData.hotelLat, true);
        mActivity = getActivity();
        view = inflater.inflate(R.layout.fragment_day_one, container, false);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.layout_container);
        createCardView(parent, obj11);
        createCardView(parent, obj10);
        createCardView(parent, obj9);
        createCardView(parent, obj8);
        createCardView(parent, obj7);
        createCardView(parent, obj6);
        createCardView(parent, obj5);
        createCardView(parent, obj4);
        createCardView(parent, obj3);
        createCardView(parent, obj2);
        createCardView(parent, obj1);
        return view;
    }

    private void createCardView(LinearLayout parentLinearLayout, final Card cardObject) {
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(mActivity.LAYOUT_INFLATER_SERVICE);
        View cardView = inflater.inflate(R.layout.card_layout, null);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 15, 15, 15);
        cardView.setLayoutParams(params);
        LayoutTransition layoutTransition = parentLinearLayout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        LinearLayout llHeader = cardView.findViewById(R.id.ll_card_header);
        final LinearLayout llExtension = cardView.findViewById(R.id.ll_card_extentsion);
        final ImageView imageIcon = (ImageView) cardView.findViewById(R.id.iv_card_icon);
        TextView textViewTiming = (TextView) cardView.findViewById(R.id.tv_card_time);
        TextView textViewHeading = (TextView) cardView.findViewById(R.id.tv_card_heading);
        TextView textViewDesc = (TextView) cardView.findViewById(R.id.tv_card_desc);
        TextView textViewOwner = (TextView) cardView.findViewById(R.id.tv_card_owner);
        Button btnNotify = (Button) cardView.findViewById(R.id.btn_remind);
        Button btnNavigate = (Button) cardView.findViewById(R.id.btn_navigate);
        textViewTiming.setText("Timming: " + cardObject.getTimming());
        textViewHeading.setText(cardObject.getHeading());
        textViewDesc.setText(cardObject.getHeadingDesc());
        textViewOwner.setText("Owner: " + cardObject.getOwner());
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(mActivity, 0, intent, 0);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mActivity, "event")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(cardObject.getHeading())
                        .setContentText(cardObject.getHeading())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(cardObject.getHeadingDesc()))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mActivity);
                notificationManager.notify(0, mBuilder.build());
            }
        });
        if (cardObject.isTravel()) {
            btnNavigate.setVisibility(View.VISIBLE);
            btnNavigate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + cardObject.getLatitude() + "," + cardObject.getLongitude());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });
        } else {
            btnNavigate.setVisibility(View.GONE);
        }

        llHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llExtension.getVisibility() == View.VISIBLE) {
                    llExtension.setVisibility(View.GONE);
                    imageIcon.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                } else {
                    llExtension.setVisibility(View.VISIBLE);
                    imageIcon.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
            }
        });
        parentLinearLayout.addView(cardView, parentLinearLayout.getChildCount() - 1);
    }

}
