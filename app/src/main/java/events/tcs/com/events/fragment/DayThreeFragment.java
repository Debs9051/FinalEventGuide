package events.tcs.com.events.fragment;


import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import events.tcs.com.events.R;
import events.tcs.com.events.constant.ApplicationData;
import events.tcs.com.events.data.Card;
import events.tcs.com.events.utils.AppUtils;

public class DayThreeFragment extends Fragment {
    private Activity mActivity;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set values for Card
        Card obj1 = new Card("Travel to TCS Office", "Travel to TCS Office – Gitanjali Park, complete security procedures", "9:00 AM – 10:00 AM ", "TCS", 0.0, 0.0, true);
        Card obj2 = new Card("BI Migration Project", "BI Migration Project Workshop with team", "10:00 AM – 11:00 AM ", "David and TCS team", 0.0, 0.0, false);
        Card obj3 = new Card("SAP AMS Workshop", "SAP AMS Workshop with team", "10:00 AM – 11:00AM", "Stephane and TCS team", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj4 = new Card("Presentation", "Presentation on TCS Capabilities in SuccessFactor", "11:00 AM – 12:30 PM ", "TCS SAP COE", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj5 = new Card("Lunch @ TCS Office", "Lunch and personal Time/Mails etc", "12:00 PM – 2:00 PM", "N/A", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj6 = new Card("Innovation done by the Account Team/Ideathon/Award Ceremony", "In-house Innovation done by the Account Team/Ideathon/Award Ceremony", "02:00 PM – 03:00 PM", "N/A", ApplicationData.noLat, ApplicationData.noLan, false);
        Card obj7 = new Card("Travel to Airport for Delhi", "Travel to Airport for Delhi", "03:30 PM – 4:30 PM", "N/A", ApplicationData.noLat, ApplicationData.noLan, false);
        mActivity = getActivity();
        view = inflater.inflate(R.layout.fragment_day_one, container, false);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.layout_container);
        createCardView(parent, obj1);
        createCardView(parent, obj2);
        createCardView(parent, obj3);
        createCardView(parent, obj4);
        createCardView(parent, obj5);
        createCardView(parent, obj6);
        createCardView(parent, obj7);
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
        textViewDesc.setText(Html.fromHtml(cardObject.getHeadingDesc()));
        textViewOwner.setText("Owner: " + cardObject.getOwner());
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.scheduleNotification(AppUtils.getNotification(cardObject.getHeading(), cardObject.getHeadingDesc(), mActivity), "2018/06/20 " + cardObject.getTimming().substring(0, cardObject.getTimming().indexOf("–") - 1), mActivity);
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
        parentLinearLayout.addView(cardView, parentLinearLayout.getChildCount());
    }
}
