package com.example.miaojie.ptest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miaojie.ptest.Activity.DetailedActivity;
import com.example.miaojie.ptest.Activity.MainActivity;
import com.example.miaojie.ptest.Adapter.RCadapter;
import com.example.miaojie.ptest.bean.MovieInfo;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.RecycleViewDivider;


import java.util.ArrayList;



/**
 * Created by miaojie on 2017/3/17.
 */

public class MoiveListFragment extends Fragment {
    private ArrayList<MovieInfo>list;
    private RecyclerView recyclerView;
    private Button button;
    private SearchView searchView;
    private ArrayList<MovieInfo>SearchList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_movielist,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        button= (Button) view.findViewById(R.id.Movie_Item_Buy);

        list=new ArrayList<>();
        setData();
        searchView= (SearchView) view.findViewById(R.id.movieSearchView);

        RCadapter adapter=new RCadapter(getContext(),list);
        adapter.setListener(new RCadapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position=recyclerView.getChildAdapterPosition(view)-1;
                MainActivity.orderInfo.setMovieName(list.get(position).getName());
                MainActivity.orderInfo.setPrice(list.get(position).getPrice());
                Intent intent=new Intent(getContext(), DetailedActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("MovieInfo",list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setHeadView(inflater.inflate(R.layout.fragment_movielist_head,null,false));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("asd",newText);
                SearchList.clear();
                for(int i=0;i<list.size();i++)
                {
                    if (list.get(i).getName().contains(newText))
                    {
                        SearchList.add(list.get(i));
                        Log.e("asd","setList"+SearchList.size());
                    }
                }
                if (SearchList.size()>0||newText.length()>0)
                {
                    Log.e("asd","setList"+SearchList.size());
                    adapter.setList(SearchList);
                    recyclerView.setAdapter(adapter);
                }
                if(newText.length()==0)
                {
                    adapter.setList(list);
                    recyclerView.setAdapter(adapter);
                }


                return false;
            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),RecyclerView.HORIZONTAL));
        return view;
    }
    private void setData()
    {
        MovieInfo movie1=new MovieInfo();
        movie1.setCoverId(R.mipmap.movie_cover1);
        movie1.setDetailedCover(R.mipmap.movie_detailpic1);
        movie1.setDirector("ղķ˹�����ߵ�");
        movie1.setIntroduce("�����˴�����٣�Xս���Ѿ���ɢ������ƣ����ĺ���޸�������������ʧ���������ƶ��գ�������˾���������ī����߾������չ��ű��ܲ�ʹ��ĥ�Ĳ��˹����ά����ͻȻ��һ�죬һλİ��Ů�����޸�ȥ��һ���������������Ů��ȥ���ô�߾���һ��ʼ�޸��ܾ��ˣ������˹����ά��һֱ�ڵ������Ů���ĳ��֣���Ϊ����������ӵ�г�ǿ��ս��������������෽�涼�������ǡ�����һ��ǿ��˾��Ļ��������׷�٣���Ϊ����DNA�����������޸������ܣ��ɴ�һ������ֹ��׷����ʼ");
        movie1.setName("�����3������һս");
        movie1.setPrice(28);
        movie1.setUrl("http://www.iqiyi.com/w_19ruidn2zx.html#curid=7654200309_b165b05f95d15c1bf6d05816f665cd4b");
        list.add(movie1);

//        MovieInfo movie3=new MovieInfo();
//        movie3.setCoverId(R.mipmap.movie_cover2);
//        movie3.setDetailedCover(R.mipmap.movie_detailpic2);
//        movie3.setName("");
//        movie3.setDirector("");
//        movie3.setProtagonist("");
//        movie3.setIntroduce("");
//        list.add(movie3);

        MovieInfo movie2=new MovieInfo();
        movie2.setCoverId(R.mipmap.movie_cover2);
        movie2.setDetailedCover(R.mipmap.movie_detailpic2);
        movie2.setDirector("��������˹�ء������������");
        movie2.setIntroduce("һ����ΪNerve�����˴�ð��ֱ����Ϸ��ϯ��ŦԼ�ǣ���Ϸ��������������ͬ��ѡ��Ҫô�����ۿ��ߡ���Ҫô������ҡ����ۿ��߸��Ѿ������ȥ��ս����ð����Ϸ��\n" + "����Ů��Vee�����ꡤ�޲����Σ��Ǹ�ƽ������ĹԹ�Ů��ƽ��������Ȥ���ǡ����ӡ�ѧУ�������JP�����ĺ���Sydney�����������ֱ����Ϸ�Ϊ�������ۿ��߶��������ִ���Ϊ������Ӱ�죬ƽ��ϰ���ڵ��Թ��ߵ�Vee��Ҳ���������ֱ����Ϸ����Ϊ�ˡ���ҡ������ĵ�һ��ð�գ���������һ��İ���ˡ���ս�ɹ������ŷ������İ����Ian�����򡤸������Σ�Ҳ����Ϸ���֮һ���������Ĵ�ð��ֱ�����Ӹտ�ʼ�ĺ���̼�����������ʧ�ء����Ǳ���Я���ӳ��ⳡ����ֱ����");
        movie2.setName("����ֱ��");
        movie2.setProtagonist("���ꡤ�޲��ģ�����Ҷ�ء�����˹�����򡤸�����");
        movie2.setPrice(28);
        movie2.setUrl("https://v.qq.com/x/page/t036027zzt7.html");
        list.add(movie2);

        MovieInfo movie3=new MovieInfo();
        movie3.setCoverId(R.mipmap.movie_cover3);
        movie3.setDetailedCover(R.mipmap.movie_detailpic3);
        movie3.setName("���첩ʿ");
        movie3.setDirector("˹���ء������ɭ");
        movie3.setProtagonist("����Ͽ��ء��������棬�����ء����Ӹ��أ�������");
        movie3.setIntroduce("�����첩ʿ��������������Ӱҵ��������ʿ��Ӱҵ��˾��Ʒ����ö�����Ӱ����˹���ء������ɭִ��,������Ͽ��ء��������桢�����ء����Ӹ��ء��������ǵ�˹���ٶ��˹�Ķ��������ݡ�\n" +
                "��Ƭ�����������ҽ��ʷ�ٷҡ�˹��������һ�γ�����ʧȥ��˫�ֵ���������������ص�����ħ��ʦ�İ�����������Ϊ��ӵ�г���ħ�������첩ʿ��\n" +
                "��Ƭ��2016��11��4����3D��IMAX 3D���й���Ļ3D�汾���й�������ͬ����ӳ");
        movie3.setPrice(28);
        movie3.setUrl("http://www.iqiyi.com/w_19rsyywfe9.html");
        list.add(movie3);

        MovieInfo movie4=new MovieInfo();
        movie4.setCoverId(R.mipmap.movie_cover4);
        movie4.setDetailedCover(R.mipmap.movie_detailpic4);
        movie4.setName("¿��ˮ");
        movie4.setDirector("���ꡢ��¶");
        movie4.setProtagonist("����ϫ / ���� / ��˧�� / ���ɽ / ������ / ");
        movie4.setIntroduce("һȺ��Ʒ�в��ˡ�ȴ������������Ĵ�ѧ��ʦ���Ӵ��������ƫԶ��忪����һ��СѧУ��ѧУ�����ҵ��������࣬����ʦ�Ƕ��Ե����֣�ÿ�������������һƬ��Ȼ������������ԱҪ��ͻ��������Ϣ�����˰�������ΪѧУ��һλ��¿��ˮ��ʦ�������Ų��ɸ��˵����ܡ����������˶����ĳ��¼�����¶��ʱ��һ��������ŵĳ��������˴�ң�Ȼ��˭���ϵ��������鷳�Ÿոտ�ʼ����");
        movie4.setPrice(28);
        movie4.setUrl("http://www.iqiyi.com/w_19rsts6kgt.html");
        list.add(movie4);

        MovieInfo movie5=new MovieInfo();
        movie5.setCoverId(R.mipmap.movie_cover5);
        movie5.setDetailedCover(R.mipmap.movie_detailpic5);
        movie5.setName("�ع����ж�");
        movie5.setDirector("�ֳ���");
        movie5.setPrice(28);
        movie5.setProtagonist("�ź��裬�����̣����ľ꣬����");
        movie5.setIntroduce("2011��10��5���峿�������й��̴����ع��ӽ�����������Ϯ������13���й���Աȫ�����ѣ����ڴ��Ϸ���90������Ʒ������ǹɱʮ�����й���Ա��Ѫ��ԩ���������˱������Ļ�����ìͷָ���й��˶����ߵ��Ƿǵ����ۣ�Ϊ�˻�����ͬ��һ����ף��й������ɳ�������Ӣ����ɴ˴ΰ������ر��ж�С�飬�Ը߸գ��ź����Σ�Ϊ�ӳ���Ǳ������ǲ������࣬��������������Ļ����֡�Ȼ���������׵Ĺ��̲������������ô�򵥣��¼��Ľ�չ��˷���룬ͨ������ĵ�·����������");
        movie5.setUrl("http://www.iqiyi.com/v_19rrljt76s.html");
        list.add(movie5);
    }

}
