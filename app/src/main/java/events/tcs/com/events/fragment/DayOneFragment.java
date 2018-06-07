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
import events.tcs.com.events.data.Card;

public class DayOneFragment extends Fragment {
    private Activity mActivity;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set values for Card
        Card obj1 = new Card("Travel to TCS Office", "Travel to TCS Office – Gitanjali Park, complete security procedures", "10:00 AM – 11:00 AM ", "TCS", 0.0, 0.0, true);
        Card obj2 = new Card("Welcome address and agenda discussion", "Welcome address, visit expectations and agenda discussion\n" +
                "• Introduction with TCS Senior Management at Kolkata\n" +
                "• Meet with TCS Offshore Team Leads\n" +
                "Expectations from the Visit\n", "11:00 AM – 11:45 AM ", "TCS\n" +
                "Sephora", 0.0, 0.0, false);
        mActivity = getActivity();
        view = inflater.inflate(R.layout.fragment_day_one, container, false);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.layout_container);
        createCardView(parent, obj1);
        createCardView(parent, obj2);
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
