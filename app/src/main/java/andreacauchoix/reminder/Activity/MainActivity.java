package andreacauchoix.reminder.Activity;

        import java.util.ArrayList;
        import java.util.List;

        import android.content.ComponentName;
        import android.content.Context;
        import android.content.Intent;
        import android.content.ServiceConnection;
        import android.os.Bundle;
        import android.app.Activity;
        import android.app.Fragment;
        import android.app.FragmentManager;
        import android.content.res.Configuration;
        import android.os.IBinder;
        import android.support.v4.app.ActionBarDrawerToggle;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.Toast;

        import andreacauchoix.reminder.AdapteurDesListes.CustomDrawerAdapter;
        import andreacauchoix.reminder.AdapteurDesListes.DrawerItem;
        import andreacauchoix.reminder.Fragments.AjoutLieu;
        import andreacauchoix.reminder.Fragments.AjoutRappel;
        import andreacauchoix.reminder.Fragments.MenuPrincipal;
        import andreacauchoix.reminder.Fragments.SuppressionLieu;
        import andreacauchoix.reminder.Fragments.SuppressionRappel;
        import andreacauchoix.reminder.R;

/**
 * Created by Andréa on 14/04/2015.
 */

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, MyService.class));

        // Initializing
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        // Add Drawer Item to dataList
        dataList.add(new DrawerItem(" "+getString(R.string.title_0), R.drawable.ic_action_about));
        dataList.add(new DrawerItem(" "+getString(R.string.title_1), R.drawable.ic_man));
        dataList.add(new DrawerItem(" "+getString(R.string.title_2), R.drawable.ic_rubber));
        dataList.add(new DrawerItem(" "+getString(R.string.title_3), R.drawable.ic_heart));
        dataList.add(new DrawerItem(" "+getString(R.string.title_4), R.drawable.ic_travel));


        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new MenuPrincipal();
                args.putString(MenuPrincipal.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(MenuPrincipal.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 1:
                fragment = new AjoutRappel();
                args.putString(AjoutRappel.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(AjoutRappel.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 2:
                fragment = new SuppressionRappel();
                args.putString(SuppressionRappel.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(SuppressionRappel.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 3:
                fragment = new AjoutLieu();
                args.putString(AjoutLieu.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(AjoutLieu.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 4:
                fragment = new SuppressionLieu();
                args.putString(SuppressionLieu.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(SuppressionLieu.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }

}