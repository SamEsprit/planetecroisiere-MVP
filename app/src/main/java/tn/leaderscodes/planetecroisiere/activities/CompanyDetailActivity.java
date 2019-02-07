package tn.leaderscodes.planetecroisiere.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.BoatCompanyAdapter;
import tn.leaderscodes.planetecroisiere.adapters.TravelAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.contracts.CompanyDetailActivityContract;
import tn.leaderscodes.planetecroisiere.presenters.CompanyDetailActivityPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelSubRegionRequest;

import static tn.leaderscodes.planetecroisiere.tools.URLS.EndPointCompanyImg;

public class CompanyDetailActivity extends BaseActivity implements CompanyDetailActivityContract.View {

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private CruiseLine cruiseLine;
    List<Boat> boatsSpinner=new ArrayList<>();
    private TextView company_name_txt;
    private TextView company_desc_txt;
    private TextView cruise_name, navire_company;
    private TextView dateMin;
    private TextView dateMax;
    private TextView more, no_data;

    private ImageView company_img;
    private Button search;

    private RecyclerView companyBoat_rv;
    private RecyclerView company_travel_rv;

    private Spinner destination_spn;
    private Spinner boat_spn;

    private CardSliderLayoutManager layoutManger;
    private RecyclerView.LayoutManager lineareLayoutManager;

    private BoatCompanyAdapter boatCompanyAdapter;
    private TravelAdapter travelAdapter;

    private TravelRequest travelRequest;

    ArrayAdapter destination_spn_ArrayAdapter;
    ArrayAdapter boat_spn_ArrayAdapter;

    private CompanyDetailActivityContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        initializeToolBar();
        initializeUtils();
        travelRequest = new TravelRequest();
        travelRequest.setCompany(cruiseLine.getCode());
        presenter.do_showBoatsList();
        presenter.do_showDestinationsList();
        presenter.do_showTravelsList(travelRequest);

    }


    @Override
    public void showBoatsList(List<Boat> boats) {
        boatsSpinner.addAll(boats);
        companyBoat_rv.setHasFixedSize(true);
        boatCompanyAdapter.setmItems(boats);
        companyBoat_rv.setAdapter(boatCompanyAdapter);
        layoutManger = (CardSliderLayoutManager) companyBoat_rv.getLayoutManager();
        new CardSnapHelper().attachToRecyclerView(companyBoat_rv);

        boatCompanyAdapter.setOnItemClickListener((view, obj, position) -> {
            Intent intent = new Intent(getApplicationContext(), BoatDetailActivity.class);
            intent.putExtra("Boat", obj);
            startActivity(intent);
        });
        boatsSpinner.add(0, new Boat("Navire"));
        boat_spn_ArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, boatsSpinner);
        boat_spn.setAdapter(boat_spn_ArrayAdapter);

    }

    @Override
    public void showTravelsList(List<Travel> travels) {
        if (travels.size() != 0) {
            no_data.setVisibility(View.GONE);
            company_travel_rv.setHasFixedSize(true);
            travelAdapter.setmItems(travels);
            company_travel_rv.setAdapter(travelAdapter);
            company_travel_rv.setLayoutManager(lineareLayoutManager);
            company_travel_rv.setVisibility(View.VISIBLE);
            more.setVisibility(View.VISIBLE);

        } else {
            no_data.setVisibility(View.VISIBLE);
            company_travel_rv.setVisibility(View.GONE);
            more.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDestinationsList(List<SubRegion> subRegions) {
        subRegions.add(0, new SubRegion("Destination"));
        destination_spn_ArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, subRegions);
        destination_spn.setAdapter(destination_spn_ArrayAdapter);
    }

    @Override
    public void initializeToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");
        company_name_txt.setText(cruiseLine.getName());
    }

    @Override
    public void initializeView() {
        setContentView(R.layout.activity_company_detail_avtivity);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "CruiseLine");
        cruiseLine = (CruiseLine) getIntent().getSerializableExtra("CruiseLine");

        companyBoat_rv = findViewById(R.id.companyBoat_rv);
        company_travel_rv = findViewById(R.id.company_travel_rv);

        company_img = findViewById(R.id.image);
        Glide.with(getApplicationContext()).load(EndPointCompanyImg+cruiseLine.getImage()).thumbnail(0.5f).into(company_img);
        company_desc_txt = findViewById(R.id.company_desc_txt);
        company_name_txt = findViewById(R.id.company_name_txt);
        cruise_name = findViewById(R.id.cruise_name);
        more = findViewById(R.id.more);
        navire_company = findViewById(R.id.navire_company);
        no_data = findViewById(R.id.no_data);
        dateMin = findViewById(R.id.dateMin);
        dateMax = findViewById(R.id.dateMax);

        search = findViewById(R.id.search);

        boat_spn = findViewById(R.id.boat_spn);
        destination_spn = findViewById(R.id.destination_spn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            company_desc_txt.setText(Html.fromHtml(cruiseLine.getLong_text(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            company_desc_txt.setText(Html.fromHtml(cruiseLine.getLong_text()));
        }

        cruise_name.setText(cruise_name.getText().toString() + " " + cruiseLine.getName());
        navire_company.setText(navire_company.getText().toString() + " " + cruiseLine.getName());

        boat_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    travelRequest.setBoat(((Boat) parent.getSelectedItem()).getCode());
                else  travelRequest.setBoat("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                travelRequest.setBoat("");
            }
        });
        destination_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    TravelSubRegionRequest travelSubRegionRequest = new TravelSubRegionRequest();
                    travelSubRegionRequest.setCode(((SubRegion) parent.getSelectedItem()).getCode());
                    travelSubRegionRequest.setSubRegionsCorps(((SubRegion) parent.getSelectedItem()).getSubRegionsCorps());
                    travelRequest.setSubRegionRequest(travelSubRegionRequest);
                }
                else  travelRequest.setSubRegionRequest(new TravelSubRegionRequest());
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
                        if (monthOfYear < 10)
                            month = "0" + monthOfYear;
                        else month = String.valueOf(monthOfYear);
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
                    .setPreselectedDate(2019, 1, 20)
                    .setOnDateSetListener((dialog, year, monthOfYear, dayOfMonth) -> {
                        String month = "";
                        String day = "";
                        if (monthOfYear < 10)
                            month = "0" + monthOfYear;
                        else month = String.valueOf(monthOfYear);
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
        layoutManger = new CardSliderLayoutManager(getApplicationContext());
        lineareLayoutManager = new LinearLayoutManager(getApplicationContext());
        presenter = new CompanyDetailActivityPresenter(this, cruiseLine.getCode());
        boatCompanyAdapter = new BoatCompanyAdapter(getApplicationContext());
        travelAdapter = new TravelAdapter(getApplicationContext());
    }

    // give preparation animation activity transition
    public static void navigate(AppCompatActivity activity, View transitionImage, CruiseLine obj) {
        Intent intent = new Intent(activity, CompanyDetailActivity.class);
        intent.putExtra("CruiseLine", obj);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, "CruiseLine");
        ActivityCompat.startActivity(activity, intent, options.toBundle());
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
