using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ButtonLogic : MonoBehaviour {

     public Button tower1;
     public Button tower2;
     public Button tower3;

    public GameObject tower1Pre;
    public GameObject tower2pre;
    public GameObject tower3pre;

    public GameObject cube;

    public int towerNum = 0;

	// Use this for initialization
	void Start () {

        tower1.onClick.AddListener(onTower1);
        tower2.onClick.AddListener(onTower2);
        tower3.onClick.AddListener(onTower3);
    }
	
    void onTower1()
    {
        if (!StaticClasses.getActive(towerNum) && StaticClasses.Score >= 100)
        {
            Debug.Log("Click 1");
            StaticClasses.towers[towerNum -1] = GameObject.Instantiate(tower1Pre);
            StaticClasses.towers[towerNum -1].transform.position = new Vector3(this.cube.transform.position.x, this.cube.transform.position.y, this.cube.transform.position.z);
            StaticClasses.toggleActive(this.towerNum);
            StaticClasses.toggleTower(towerNum);
            StaticClasses.Score -= 100;

            TowerHealth.towerHealth[towerNum - 1].gameObject.SetActive(true);
        }
    }
    void onTower2()
    {
        if (!StaticClasses.getActive(towerNum) && StaticClasses.Score >= 100)
        {
            GameObject g = tower2pre;
            g.transform.position = new Vector3(this.cube.transform.position.x, this.cube.transform.position.y, this.cube.transform.position.z);
            StaticClasses.towers[towerNum - 1] = GameObject.Instantiate(g);
            StaticClasses.toggleActive(this.towerNum);
            StaticClasses.toggleTower(towerNum);
            Debug.Log("Click 2");
            StaticClasses.Score -= 100;

     
            TowerHealth.towerHealth[towerNum - 1].gameObject.SetActive(true);
        }
    }

    void onTower3()
    {
        if (!StaticClasses.getActive(towerNum) && StaticClasses.Score >= 100)
        {
            GameObject g = tower3pre;
            g.transform.position = new Vector3(this.cube.transform.position.x, this.cube.transform.position.y, this.cube.transform.position.z);
            StaticClasses.towers[towerNum - 1] = GameObject.Instantiate(g);
            StaticClasses.toggleActive(this.towerNum);
            StaticClasses.toggleTower(towerNum);
            Debug.Log("Click 3");
            StaticClasses.Score -= 100;

            TowerHealth.towerHealth[towerNum - 1].gameObject.SetActive(true);
        }
    }
}
