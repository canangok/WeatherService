package com.example.canan.havadurumu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView txt_havadurumu,txt_sehir,txt_aciklama,txt_sicaklik,textView,txtDerece,textHava_durumu,textAciklama;
    private EditText editText_sehir;
    private Button btn_havadurumuGetir;
    private ImageView image;
    String sehir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        txt_havadurumu = (TextView) findViewById(R.id.txtHavaDurumu);
        txt_sehir = (TextView) findViewById(R.id.txtSehir);
        txt_aciklama = (TextView) findViewById(R.id.txtAciklama);
        txt_sicaklik = (TextView) findViewById(R.id.txtSicaklik);
        txtDerece=(TextView) findViewById(R.id.txtDerece);
        textHava_durumu =(TextView) findViewById(R.id.texthava_durumu);
        textAciklama=(TextView) findViewById(R.id.textAciklama);
        image=(ImageView) findViewById(R.id.imageView);
        editText_sehir = (EditText) findViewById(R.id.editTxtSehir);


        btn_havadurumuGetir = (Button) findViewById(R.id.btnGetir);
        btn_havadurumuGetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sehir=String.valueOf(editText_sehir.getText());
                new JsonParse().execute();
            }
        });

    }
    protected class JsonParse extends AsyncTask<Void ,Void,Void> {
        String result_main = "";
        String result_aciklama = "";
        String result_ikon = "";
        int result_sicaklik;
        String result_sehir = "";
        Bitmap bitImage;

        @Override
        protected Void doInBackground(Void... voids) {
            String result = "";
            try {
                URL weather_url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + sehir + "&lang=tr&appid=9fdd11094d3f97fb401c6f0cb8be93a5");
                BufferedReader reader = null;
                reader = new BufferedReader(new InputStreamReader(weather_url.openStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("weather");

                JSONObject jsonObject_weather = jsonArray.getJSONObject(0);

                result_main = jsonObject_weather.getString("main");
                switch (result_main) {
                    case "Clear":
                        result_main = "açık";
                    case "Clear sky":
                        result_main = "açık hava";
                        break;
                    case "Clouds":
                        result_main = "bulutlu";
                        break;
                    case "Few clouds":
                        result_main = "az bulutlu";
                        break;
                    case "Scattered clouds":
                        result_main = "az bulutlu";
                        break;
                    case "Broken clouds":
                        result_main = "parçalı bulutlu";
                        break;
                    case "Shower rain":
                        result_main = "sağanak yağmurlu";
                        break;
                    case "Rain":
                        result_main = "yağmurlu";
                        break;
                    case "Thunderstorm":
                        result_main = "gök gürültülü sağanak";
                        break;
                    case "Snow":
                        result_main = "karlı";
                        break;
                    case "Mist":
                        result_main = "sisli";
                        break;
                    case "Fog":
                        result_main = "sis";
                        break;
                    case "Tornado":
                        result_main = "kasırga";
                        break;
                    case "Cold":
                        result_main = "soğuk";
                        break;
                    case "Hot":
                        result_main = "sıcak";
                        break;
                    case "Windy":
                        result_main = "rüzgarlı";
                        break;
                    case "Hail":
                        result_main = "dolu";
                        break;
                    case "Storm":
                        result_main = "sisli";
                        break;
                    case "Squalls":
                        result_main = "ani ve şiddetli rüzgar";
                        break;

                    default:
                        break;
                }
                    result_aciklama = jsonObject_weather.getString("description");
                result_ikon = jsonObject_weather.getString("icon");

                JSONObject jsonObject_main = jsonObject.getJSONObject("main");
                Double sicaklik = jsonObject_main.getDouble("temp");

                result_sehir = jsonObject.getString("name");
                result_sicaklik = (int) (sicaklik - 273);

                URL ikon_url = new URL("http://openweathermap.org/img/w/" + result_ikon + ".png");
                bitImage = BitmapFactory.decodeStream(ikon_url.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            txt_sicaklik.setText(String.valueOf(result_sicaklik));
            txt_havadurumu.setText(result_main);
            txt_sehir.setText(result_sehir);
            txt_aciklama.setText(result_aciklama);
            textHava_durumu.setText("Hava Durumu : ");
            textAciklama.setText("Açıklama : ");
            textView.setText("o");
            txtDerece.setText("derece");
            image.setImageBitmap(bitImage);
            super.onPostExecute(aVoid);
        }
    }


}
