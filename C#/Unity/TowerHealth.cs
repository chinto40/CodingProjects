using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TowerHealth : MonoBehaviour
{

    //public Slider slide;
    public static Slider[] towerHealth = new Slider[4];


    // Start is called before the first frame update
    void Start()
    {

        towerHealth[0] = GameObject.FindGameObjectWithTag("tower1Health").GetComponent<Slider>();
        towerHealth[1] = GameObject.FindGameObjectWithTag("tower2Health").GetComponent<Slider>();
        towerHealth[2] = GameObject.FindGameObjectWithTag("tower3Health").GetComponent<Slider>();
        towerHealth[3] = GameObject.FindGameObjectWithTag("tower4Health").GetComponent<Slider>();

        int i;
        for (i = 0; i < 4; i++)
        {
            towerHealth[i].value = 100;
            towerHealth[i].interactable = false;
            towerHealth[i].gameObject.SetActive(false);
        }
        

    }

    // Update is called once per frame
    void Update()
    {
        int i;
        for (i = 0; i < 4; i++)
        {
            towerHealth[i].value = StaticClasses.newTowerLife[i];
            if (towerHealth[i].value <= 0)
            {
                Destroy(StaticClasses.towers[i]);
                StaticClasses.toggleActive(i + 1);
                StaticClasses.newTowerLife[i] = 100;
                towerHealth[i].value = 100;
                towerHealth[i].gameObject.SetActive(false);
                StaticClasses.toggleTower(i+1);
                //kill tower
                Debug.Log("temp");
            }
        }
       
    }

    
}