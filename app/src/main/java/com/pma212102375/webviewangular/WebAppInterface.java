package com.pma212102375.webviewangular;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.icu.text.CaseMap.Title;
import android.net.Uri;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import org.w3c.dom.Text;

public class WebAppInterface {

    private Activity _activity;
    private Context _context;

    public WebAppInterface(Context context, Activity activity){
        _context = context;
        _activity = activity;
    }

    @JavascriptInterface
    public void showToast(String message){
        Toast.makeText(_context, message, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void showSms(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
        _context.startActivity(intent);
    }


    @JavascriptInterface
    public void showCall() {
        Intent kontakIntent = new Intent(Intent.ACTION_MAIN);
        kontakIntent.addCategory(Intent.CATEGORY_APP_CONTACTS);
        _context.startActivity(kontakIntent);
    }

    @JavascriptInterface
    public void showNotification(String title, String message) {
        NotificationChannel channel = new NotificationChannel("twChannel", "TW", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(_context.getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(channel.getId());

        NotificationManager manager = (NotificationManager) _context.getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1, builder.build());
    }

    @JavascriptInterface
    public void showWhatsApp() {
        String url = "https://api.whatsapp.com/send?phone=" + "081255178949";
        Intent waIntent = new Intent(Intent.ACTION_VIEW);
        waIntent.setData(Uri.parse(url));
        _context.startActivity(waIntent);
    }

    @JavascriptInterface
    public void showCamera() {
        Intent kameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        _context.startActivity(kameraIntent);
    }

}