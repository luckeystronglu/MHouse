package com.qf.luckey.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.luckey.entity.CityEntity;
import com.qf.luckey.entity.NewsEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * List<CityEntity>
 *     ->CityEntity: cityname:A type:0
 *     ->CityEntity: cityname:安阳 type:1
 *     ->CityEntity: cityname:安庆 type:1
 *     ->CityEntity: cityname:安山 type:1
 *     ->CityEntity: cityname:B type:0
 *     ->CityEntity: cityname:北京 type:1
 *     ->CityEntity: cityname:北平 type:1
 *     ->CityEntity: cityname:北海 type:1
 * Created by Administrator on 2016/8/7.
 */
public class JsonUitl {
    private static String[] letterStrs = {"hotcities","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
   //解析城市列表的json
    public static List<CityEntity> getCitysByJson(String json){
        //放一个list总的集合
        List<CityEntity> cityEntities = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = jsonObject.getJSONObject("cities");
            for (int i = 0;i < letterStrs.length;i++){
                JSONArray jsonArray = jsonObject.optJSONArray(letterStrs[i]);
                if (jsonArray != null){
                    if (letterStrs[i].equals("hotcities")){
                        cityEntities.add(new CityEntity("热门城市",0));
                    }else {
                        //将字母作为城市实体对象加入总集合
                        cityEntities.add(new CityEntity(letterStrs[i], 0));
                    }
                    TypeToken<List<CityEntity>> tt = new TypeToken<List<CityEntity>>(){};
                    List<CityEntity> citylist = new Gson().fromJson(jsonArray.toString(),tt.getType());
                    //将字母对应的实体城市集合添加进总集合
                    cityEntities.addAll(citylist);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityEntities;
    }


    //根据json解析得到相应的数据实体集合
    public static List<NewsEntity> getNewsByJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");

            TypeToken<List<NewsEntity>> tt = new TypeToken<List<NewsEntity>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
