using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Can : MonoBehaviour {

    public Text Score;
    public Text Time;
    public Text NumOfEne;
    public Text powerTime;
    public int dur = StaticClasses.dur;
    public int timeStamp;
    //public Slider playerlife;

    // Use this for initialization
    void Start()
    {
        Debug.Log(StaticClasses.numOfEnemies);
       // Debug.Break();
    }

    // Update is called once per frame
    void Update()
    {
        this.Score.text = "Points: " + StaticClasses.Score;
        this.Time.text = "Time: " + StaticClasses.sec;
        this.NumOfEne.text = "Enemies:" + StaticClasses.numOfEnemies;
        
        if(StaticClasses.powerActive == true)
        {
            
            this.powerTime.gameObject.SetActive(true);
            this.powerTime.text = "PowerDur: " + (Mathf.Abs(StaticClasses.sec - StaticClasses.powerEndTime));
        }
        else
        {
            this.powerTime.gameObject.SetActive(false);
        }
       // this.playerlife.value = StaticClasses.playerLife;
    }

}
