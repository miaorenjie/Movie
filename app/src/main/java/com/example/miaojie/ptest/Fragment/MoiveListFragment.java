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
        movie1.setDirector("詹姆斯·曼高德");
        movie1.setIntroduce("变种人大幅减少，X战警已经解散。身心疲惫的暮狼罗根自愈因子逐渐消失，整天饮酒度日，并靠当司机生活，他在墨西哥边境隐居照顾着饱受病痛折磨的查尔斯·泽维尔。突然有一天，一位陌生女子让罗根去载一个叫劳拉·金尼的女孩去加拿大边境，一开始罗根拒绝了，但查尔斯·泽维尔一直在等着这个女孩的出现，因为劳拉·金尼拥有超强的战斗力，而且在许多方面都很像金刚狼。她被一个强大公司的幕后人物所追踪，因为她的DNA里有着连接罗根的秘密，由此一场无休止的追捕开始");
        movie1.setName("金刚狼3：殊死一战");
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
        movie2.setDirector("亨利·朱斯特、阿里尔·舒曼");
        movie2.setIntroduce("一款名为Nerve的真人大冒险直播游戏正席卷纽约城，游戏参与者有两个不同的选择，要么当“观看者”，要么当“玩家”，观看者付费决定玩家去挑战各种冒险游戏。\n" + "高中女生Vee（艾玛·罗伯茨饰）是个平凡无奇的乖乖女，平日最大的乐趣就是“窥视”学校里的男神JP。她的好友Sydney正沉迷于这个直播游戏里，为了吸引观看者而作出种种大胆行为。受其影响，平日习惯于当旁观者的Vee，也加入了这个直播游戏，成为了“玩家”。她的第一项冒险，就是亲吻一个陌生人。挑战成功后，她才发现这个陌生人Ian（戴夫·弗兰科饰）也是游戏玩家之一。他们俩的大冒险直播，从刚开始的好玩刺激，到逐渐走向失控。他们必须携手逃出这场玩命直播。");
        movie2.setName("玩命直播");
        movie2.setProtagonist("艾玛·罗伯茨，朱丽叶特·刘易斯，戴夫·弗兰科");
        movie2.setPrice(28);
        movie2.setUrl("https://v.qq.com/x/page/t036027zzt7.html");
        list.add(movie2);

        MovieInfo movie3=new MovieInfo();
        movie3.setCoverId(R.mipmap.movie_cover3);
        movie3.setDetailedCover(R.mipmap.movie_detailpic3);
        movie3.setName("奇异博士");
        movie3.setDirector("斯科特·德瑞克森");
        movie3.setProtagonist("本尼迪克特·康伯巴奇，切瓦特·埃加福特，瑞秋·麦克");
        movie3.setIntroduce("《奇异博士》是由美国漫威影业制作，迪士尼影业公司出品的奇幻动作电影，由斯科特·德瑞克森执导,，本尼迪克特·康伯巴奇、切瓦特·埃加福特、瑞秋·麦克亚当斯、蒂尔达·斯文顿联合主演。\n" +
                "该片讲述了神经外科医生史蒂芬·斯特兰奇在一次车祸中失去了双手的能力，最后在神秘的至尊魔法师的帮助下让他成为了拥有超凡魔力的奇异博士。\n" +
                "该片于2016年11月4日以3D、IMAX 3D、中国巨幕3D版本在中国、美国同步上映");
        movie3.setPrice(28);
        movie3.setUrl("http://www.iqiyi.com/w_19rsyywfe9.html");
        list.add(movie3);

        MovieInfo movie4=new MovieInfo();
        movie4.setCoverId(R.mipmap.movie_cover4);
        movie4.setDetailedCover(R.mipmap.movie_detailpic4);
        movie4.setName("驴得水");
        movie4.setDirector("周申、刘露");
        movie4.setProtagonist("任素汐 / 大力 / 刘帅良 / 裴魁山 / 阿如那 / ");
        movie4.setIntroduce("一群“品行不端”却怀揣教育梦想的大学教师，从大城市来到偏远乡村开办了一所小学校。学校待遇惨淡、生活艰苦，但老师们都自得其乐，每天嘻嘻哈哈打成一片。然而教育部特派员要来突击检查的消息打破了安宁，因为学校有一位“驴得水老师”隐藏着不可告人的秘密。就在所有人都担心丑事即将败露的时候，一个神奇天才的出现拯救了大家，然而谁能料到真正的麻烦才刚刚开始……");
        movie4.setPrice(28);
        movie4.setUrl("http://www.iqiyi.com/w_19rsts6kgt.html");
        list.add(movie4);

        MovieInfo movie5=new MovieInfo();
        movie5.setCoverId(R.mipmap.movie_cover5);
        movie5.setDetailedCover(R.mipmap.movie_detailpic5);
        movie5.setName("湄公河行动");
        movie5.setDirector("林超贤");
        movie5.setPrice(28);
        movie5.setProtagonist("张涵予，彭于晏，冯文娟，吴旭东");
        movie5.setIntroduce("2011年10月5日清晨，两艘中国商船在湄公河金三角流域遇袭，船上13名中国船员全部遇难，并在船上发现90万粒毒品。这宗枪杀十三名中国船员的血腥冤案，掀起了悲剧的序幕。面对矛头指向中国运毒、颠倒是非的舆论，为了还遇难同胞一个清白，中国决定派出缉毒精英，组成此次案件的特别行动小组，以高刚（张涵予饰）为队长，潜入金三角查明真相，竭力揪出案件的幕后黑手。然而缉拿真凶的过程并非他们想得那么简单，事件的进展扑朔迷离，通往真相的道路更是险象环生");
        movie5.setUrl("http://www.iqiyi.com/v_19rrljt76s.html");
        list.add(movie5);
    }

}
