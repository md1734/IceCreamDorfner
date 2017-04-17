package com.example.mitch.icecreamdorfner;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    Date today = new Date();
    TextView priceView;
    CheckBox peanutsCheckBox;
    CheckBox almondsCheckBox;
    CheckBox strawberriesCheckBox;
    CheckBox gummybearsCheckBox;
    CheckBox mmCheckBox;
    CheckBox browniesCheckBox;
    CheckBox oreosCheckBox;
    CheckBox marshmallowCheckBox;
    SeekBar hotfudgeBar;
    TextView fudgeAmountTextView;
    Spinner flavorSpinner;
    String flavor;
    Spinner sizeSpinner;
    String size;
    Double peanutPrice = 0.15;
    Double almondPrice = 0.15;
    Double strawberryPrice = 0.20;
    Double gummybearPrice = 0.20;
    Double mmPrice = 0.25;
    Double browniePrice = 0.20;
    Double oreoPrice = 0.20;
    Double marshmallowPrice = 0.15;
    Double fudgePrice = 0.00;
    Double sizePrice = 0.00;
    Double price = 0.00;
    DecimalFormat getCurrency = new DecimalFormat("$###,###.00");

    ArrayList<OrderItem> orders;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        orders = new ArrayList<OrderItem>();

        priceView = (TextView) findViewById(R.id.priceTextView);
        peanutsCheckBox = (CheckBox) findViewById(R.id.peanutsCheckBox);
        almondsCheckBox = (CheckBox) findViewById(R.id.almondsCheckBox);
        strawberriesCheckBox = (CheckBox) findViewById(R.id.strawberryCheckBox);
        gummybearsCheckBox = (CheckBox) findViewById(R.id.gummybearCheckBox);
        mmCheckBox = (CheckBox) findViewById(R.id.mmCheckBox);
        browniesCheckBox = (CheckBox) findViewById(R.id.brownieCheckBox);
        oreosCheckBox = (CheckBox) findViewById(R.id.oreoCheckBox);
        marshmallowCheckBox = (CheckBox) findViewById(R.id.marshmallowCheckBox);

        hotfudgeBar = (SeekBar) findViewById(R.id.hotfudgeSeekBar);
        fudgeAmountTextView = (TextView) findViewById(R.id.fudgeAmountTextView);

        flavorSpinner = (Spinner) findViewById(R.id.flavorSpinner);
        flavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    flavor = "Vanilla";
                } else if (position == 1) {
                    flavor = "Chocolate";
                } else {
                    flavor = "Strawberry";
                }
                Toast.makeText(MainActivity.this,"You selected the flavor: " + flavor, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    size = "small";
                    price -= sizePrice;
                    sizePrice = 2.99;
                } else if (position == 1) {
                    size = "medium";
                    price -= sizePrice;
                    sizePrice = 3.99;
                } else  if (position == 2){
                    size = "large";
                    price -= sizePrice;
                    sizePrice = 4.99;
                }
                price += sizePrice;
                updateInfoDisplay();
                Toast.makeText(MainActivity.this,"You selected the size: " + size, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hotfudgeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fudgeAmountTextView.setText(Integer.toString(progress) + "oz");
                if (progress == 1) {
                    price -= fudgePrice;
                    fudgePrice = 0.15;
                } else if (progress == 2) {
                    price -= fudgePrice;
                    fudgePrice = 0.25;
                } else if (progress == 3){
                    price -= fudgePrice;
                    fudgePrice = 0.30;
                } else  if (progress == 0){
                    price -= fudgePrice;
                    fudgePrice = 0.00;
                }
                price += fudgePrice;
                updateInfoDisplay();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent i = new Intent(this, AboutActivity.class);
            i.putExtra("Title", "Frank's Ice Cream Shop");
            i.putExtra("ShopInfo", "Frank's Ice Cream Shop was founded in 2017 " +
                        "and revolutionized the ice cream delivery game entirely. Since its founding, " +
                        "the store has been voted best ice cream in town as well as becoming the largest " +
                        "delivery service in the state.");
            i.putExtra("Developer", "Developer: Mitch Dorfner");
            startActivity(i);
            return true;
        }
        if (id == R.id.action_order_history) {
            Intent i = new Intent(this, OrderHistoryActivity.class);
            i.putExtra("OrderKey", orders);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateInfoDisplay() {
        priceView.setText(getCurrency.format(price).toString());
    }

    public void processCheckBoxChanged(View view) {
        if(view.getId() == R.id.peanutsCheckBox && peanutsCheckBox.isChecked() == true) {
            price = price + peanutPrice;
        } else if (view.getId() == R.id.peanutsCheckBox && peanutsCheckBox.isChecked() == false) {
            price -= peanutPrice;
        }
        if(view.getId() == R.id.almondsCheckBox && almondsCheckBox.isChecked() == true) {
            price = price + almondPrice;
        } else if (view.getId() == R.id.almondsCheckBox && almondsCheckBox.isChecked() == false) {
            price -= almondPrice;
        }
        if(view.getId() == R.id.strawberryCheckBox && strawberriesCheckBox.isChecked() == true) {
            price = price + strawberryPrice;
        } else if (view.getId() == R.id.strawberryCheckBox && strawberriesCheckBox.isChecked() == false) {
            price -= strawberryPrice;
        }
        if(view.getId() == R.id.gummybearCheckBox && gummybearsCheckBox.isChecked() == true) {
            price = price + gummybearPrice;
        } else if (view.getId() == R.id.gummybearCheckBox && gummybearsCheckBox.isChecked() == false) {
            price -= gummybearPrice;
        }
        if(view.getId() == R.id.mmCheckBox && mmCheckBox.isChecked() == true) {
            price = price + mmPrice;
        } else if (view.getId() == R.id.mmCheckBox && mmCheckBox.isChecked() == false) {
            price -= mmPrice;
        }
        if(view.getId() == R.id.brownieCheckBox && browniesCheckBox.isChecked() == true) {
            price = price + browniePrice;
        } else if (view.getId() == R.id.brownieCheckBox && browniesCheckBox.isChecked() == false) {
            price -= browniePrice;
        }
        if(view.getId() == R.id.oreoCheckBox && oreosCheckBox.isChecked() == true) {
            price = price + oreoPrice;
        } else if (view.getId() == R.id.oreoCheckBox && oreosCheckBox.isChecked() == false) {
            price -= oreoPrice;
        }
        if(view.getId() == R.id.marshmallowCheckBox && marshmallowCheckBox.isChecked() == true) {
            price = price + marshmallowPrice;
        } else if (view.getId() == R.id.marshmallowCheckBox && marshmallowCheckBox.isChecked() == false) {
            price -= marshmallowPrice;
        }
        updateInfoDisplay();
    }

    public void processTheWorks(View view) {
        peanutsCheckBox.setChecked(true);
        almondsCheckBox.setChecked(true);
        strawberriesCheckBox.setChecked(true);
        gummybearsCheckBox.setChecked(true);
        mmCheckBox.setChecked(true);
        browniesCheckBox.setChecked(true);
        oreosCheckBox.setChecked(true);
        marshmallowCheckBox.setChecked(true);
        hotfudgeBar.setProgress(3);
        updateInfoDisplay();
    }

    public void processOrder(View view) {
        orders.add(new OrderItem(today, flavor, size, price));
        updateInfoDisplay();
    }

    public void processReset(View view) {
        peanutsCheckBox.setChecked(false);
        almondsCheckBox.setChecked(false);
        strawberriesCheckBox.setChecked(false);
        gummybearsCheckBox.setChecked(false);
        mmCheckBox.setChecked(false);
        browniesCheckBox.setChecked(false);
        oreosCheckBox.setChecked(false);
        marshmallowCheckBox.setChecked(false);
        hotfudgeBar.setProgress(1);
        sizeSpinner.setVerticalScrollbarPosition(0);
        flavorSpinner.setVerticalScrollbarPosition(0);
        hotfudgeBar.setProgress(1);
        price = 0.00;
        price = price + fudgePrice + sizePrice;
        priceView.setText(getCurrency.format(price).toString());
        updateInfoDisplay();
    }
}
