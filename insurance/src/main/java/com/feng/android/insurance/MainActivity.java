package com.feng.android.insurance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.common.ui.recyclerview.WrapRecyclerView;
import com.feng.android.insurance.model.Member;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 17:22
 * @tips
 */
public class MainActivity extends BaseActivity implements Observer<Member>{
    @BindView(R.id.rv)
    RecyclerView rv;
    PeopleAdapter mAdapter;

    private List<Member> mMembers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myUnbinder = Butterknife.bind(this);
        mAdapter = new PeopleAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);
//        TextView tv = new TextView(this);
//        tv.setText("head");
//        rv.addHeaderView(tv);
        DatabaseManager.getInstance().register(this);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void update(Member member) {
        mMembers.add(member);
        mAdapter.notifyDataSetChanged();
    }

    private class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{


        @NonNull
        @NotNull
        @Override
        public PeopleViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_people,parent,false);
            return new PeopleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull MainActivity.PeopleAdapter.PeopleViewHolder holder, int position) {
            holder.tvName.setText(mMembers.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mMembers.size();
        }

        class PeopleViewHolder extends RecyclerView.ViewHolder{
            TextView tvName;

            public PeopleViewHolder( View itemView) {
                super(itemView);
                tvName = (TextView)itemView.findViewById(R.id.name);
            }
        }

    }

    public void addMemeber(View view) {
        startActivity(InsuranceActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myUnbinder.unbind();
        //防止内存泄漏
        DatabaseManager.getInstance().unregister(this);
    }
}
