打地鼠遊戲需求
1.地洞{

  呈現:六邊形，正中間的洞跟環的洞穴不同，正中間的洞是Boss出現的洞
  
}
2.地鼠{

  種類:
      I.    一般地鼠(一分) 1~5hp 持續時間3sec
      II.   特殊地鼠(五分)(分左鍵擊打和右鍵擊打)10hp 5sec
      III.  炸彈(-1生命)(計時開始後10sec隨機出現) 1hp 持續3sec
      IV.   小Boss(二十分)(計時開始後每30sec出現在正中間)30hp 持續20sec
      V.    大Boss(50分)(計時開始後90sec出現在正中間)50hp 持續30sec
  出現方式:
    一般地鼠頻率每秒一隻，血量用random、特殊地鼠頻率5秒一隻，兩種地鼠不會同時出現。
    炸彈頻率每4秒一顆，可和地鼠同時出現。
    Boss出現時，一般地鼠和特殊地鼠不會再出現，Boss死亡後繼續生成。
    小Boss打完掉落道具(下面有道具用途)，大Boss死亡遊戲就結束。
    小Boss結束後，一般地鼠的頻率調到每秒兩隻、特殊地鼠不變，兩種地鼠會同時出現。
  
}
3.道具{

  I.火力加倍:20sec內，攻擊力翻倍，機率45%
  II.血量藥水:+1生命，機率25%
  III.力量強化:攻擊力永遠+2(原攻擊力+2) 機率20%
  IV.超級星星:10sec內，可以秒殺地鼠(除了Boss) 機率10%
  
}
4.計時方式{

  初始計時時間:1:00。
  打死一般地鼠 +1sec 打死特殊地鼠 +3sec
  地鼠持續時間內沒打死，扣地鼠剩餘血量sec
  打到炸彈，扣一格生命
  
}
5.結算方式{

  倒計時結束，或血量歸零，計算當前分數，當前出現的地鼠沒有死亡不計分，結果為最終分數
  如果在時間內打死大Boss，則剩餘時間以每秒1分加上原本的分數，結果為最終分數
  
}

6.初始化{

  Player_initial{
    Timer = 1min
    during_time = 0
    life = 3
    score = 0
  }

  Frequency{

    normal diglett = one per sec
    special diglett =  every five sec
    bomb = every four sec (during_time>=10)

    normal and special at the same time = false

    if(during_time%30 == 0 && during_time!=90){
      //可生成布林值改變
      small_boss=true;
      normal=special=bomb=false;
    }
    if(during_time==90){
      //可生成布林值改變
      large_boss=true;
      normal=special=bomb=false;
    }
    
    //when first small boss die
    normal diglett = two per sec
    normal and special at the same time = true

}
