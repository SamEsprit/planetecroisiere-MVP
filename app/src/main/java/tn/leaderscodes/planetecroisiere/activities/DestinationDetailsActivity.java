package tn.leaderscodes.planetecroisiere.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.TravelAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.contracts.DestinationDetailActivityContract;
import tn.leaderscodes.planetecroisiere.contracts.DestinationFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.DestinationDetailsPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelSubRegionRequest;
import tn.leaderscodes.planetecroisiere.remote.response.SubRegionResponse;

public class DestinationDetailsActivity extends BaseActivity implements DestinationDetailActivityContract.View {

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private SubRegion subRegion;

    private TextView destination_name_txt;
    private TextView destination_name;
    private TextView destination_dec;
    private ImageView destination_img;

    private TextView dateMin;
    private TextView dateMax;
    private TextView more, no_data;
    private Spinner cruiseLine_spn;
    private Spinner lenght_spn;
    private Button search;


    private RecyclerView destination_travel_rv;

    private DestinationDetailActivityContract.Presenter presenter;
    private RecyclerView.LayoutManager lineareLayoutManager;
    private TravelRequest travelRequest;
    private TravelAdapter travelAdapter;

    private ArrayAdapter cruiseLine_spn_ArrayAdapter;
    private ArrayAdapter length_spn_ArrayAdapter;

    private List<String> lengthArray= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        initializeUtils();
        initializeToolBar();
        travelRequest = new TravelRequest();
        travelRequest.setSubRegionRequest(new TravelSubRegionRequest(subRegion.getCode(),subRegion.getSubRegionsCorps()));
        presenter.do_showCruiseLinesList();
        presenter.do_showTravelsList(travelRequest);
    }

    @Override
    public void initializeToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");
        destination_name_txt.setText(subRegion.getName());
    }

    @Override
    public void initializeView() {
        setContentView(R.layout.activity_destination_details);
        View parent_view = findViewById(android.R.id.content);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "CruiseLine");
        subRegion = (SubRegion) getIntent().getSerializableExtra("SubRegion");


        destination_travel_rv = findViewById(R.id.destination_travel_rv);

        destination_img = findViewById(R.id.destination_img);

        destination_dec = findViewById(R.id.destination_desc);
        destination_name_txt = findViewById(R.id.destination_name_txt);
        destination_name = findViewById(R.id.destination_name);

        more = findViewById(R.id.more);

        no_data = findViewById(R.id.no_data);
        dateMin = findViewById(R.id.dateMin);
        dateMax = findViewById(R.id.dateMax);

        search = findViewById(R.id.search);

        cruiseLine_spn = findViewById(R.id.cruiseLine_spn);
        lenght_spn = findViewById(R.id.lenght_spn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            destination_dec.setText(Html.fromHtml(subRegion.getLongText(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            destination_dec.setText(Html.fromHtml(subRegion.getLongText()));
        }

        destination_name.setText(subRegion.getName());

        Glide.with(getApplicationContext()).load("https://storage.gra1.cloud.ovh.net/v1/AUTH_5f5451bc9b2b421b8a5e21db942a5510/planete-croisiere/uploads/place/casablanca-maroc_387.jpg").into(destination_img);

        cruiseLine_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    travelRequest.setCompany(((CruiseLine) parent.getSelectedItem()).getCode());
                else
                    travelRequest.setCompany("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                travelRequest.setBoat("");
            }
        });
        lenght_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    travelRequest.setLength(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                travelRequest.setSubRegionRequest(new TravelSubRegionRequest());
            }
        });

        AtomicReference<String> dateMaxString = new AtomicReference<>("");
        AtomicReference<String> dateMinString = new AtomicReference<>("");
        dateMin.setOnClickListener(v -> {
            CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                    .setPreselectedDate(2019, 1, 20)
                    .setOnDateSetListener((dialog, year, monthOfYear, dayOfMonth) -> {
                        String month = "";
                        String day = "";
                        if ((monthOfYear+1) < 10)
                            month = "0" + (monthOfYear+1);
                        else month = String.valueOf((monthOfYear+1));
                        if (dayOfMonth < 10)
                            day = "0" + dayOfMonth;
                        else day = String.valueOf(dayOfMonth);
                        dateMinString.set(year + "-" + month + "-" + day);
                    })
                    .setDoneText("Ok")
                    .setCancelText("Cancel")
                    .setOnDismissListener(dialoginterface -> {
                        dateMin.setText(dateMinString.get());
                        travelRequest.setDateMin(dateMinString.get());
                    });
            cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
        });

        dateMax.setOnClickListener(v -> {
            CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                    .setPreselectedDate(2019, 0, 20)
                    .setOnDateSetListener((dialog, year, monthOfYear, dayOfMonth) -> {
                        String month = "";
                        String day = "";
                        if ((monthOfYear+1) < 10)
                            month = "0" + (monthOfYear+1);
                        else month = String.valueOf((monthOfYear+1));
                        if (dayOfMonth < 10)
                            day = "0" + dayOfMonth;
                        else day = String.valueOf(dayOfMonth);
                        dateMaxString.set(year + "-" + month + "-" + day);
                    })
                    .setDoneText("Ok")
                    .setCancelText("Cancel")
                    .setOnDismissListener(dialoginterface -> {
                        dateMax.setText(dateMaxString.get());
                        travelRequest.setDateMax(dateMaxString.get());
                    });
            cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
        });

        search.setOnClickListener(v -> presenter.do_showTravelsList(travelRequest));

        more.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),TravelByFilterActivity.class);
            intent.putExtra("Filter", travelRequest);
            startActivity(intent);
        });
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        presenter= new DestinationDetailsPresenter(this);
        lineareLayoutManager = new LinearLayoutManager(getApplicationContext());
        travelAdapter = new TravelAdapter(getApplicationContext());
        lengthArray.add("Toutes les durées");
        lengthArray.add("De 1 à 4");
        lengthArray.add("De 5 à 7");
        lengthArray.add("De 8 à 10");
        lengthArray.add("De 11 à 15");
        lengthArray.add("16 jours et +");
        length_spn_ArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, lengthArray);
        lenght_spn.setAdapter(length_spn_ArrayAdapter);
    }

    @Override
    public void showTravelsList(List<Travel> travels) {
        if (travels.size() != 0) {
            no_data.setVisibility(View.GONE);
            destination_travel_rv.setHasFixedSize(true);
            travelAdapter.setmItems(travels);
            destination_travel_rv.setAdapter(travelAdapter);
            destination_travel_rv.setLayoutManager(lineareLayoutManager);
            destination_travel_rv.setVisibility(View.VISIBLE);
            more.setVisibility(View.VISIBLE);

        } else {
            no_data.setVisibility(View.VISIBLE);
            destination_travel_rv.setVisibility(View.GONE);
            more.setVisibility(View.GONE);
        }
    }

    @Override
    public void showCruiseLinesList(List<CruiseLine> cruiseLines) {
        cruiseLines.add(0, new CruiseLine("Compagnie"));
        cruiseLine_spn_ArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, cruiseLines);
        cruiseLine_spn.setAdapter(cruiseLine_spn_ArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
