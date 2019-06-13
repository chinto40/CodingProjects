using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class ButtonMenuRespawnPoint : MonoBehaviour {

    //[SerializeField] private Button PowerUpMenu;
    [SerializeField] private GameObject menu;
    [SerializeField] private int towerNum = 0;
    [SerializeField] private GameObject game;
    [SerializeField] private GameObject isActiveView;
    //private GameObject gameMenu;
    private Vector3 pos;


	// Use this for initialization
	void Start () {
        this.menu.transform.position = new Vector3(this.game.transform.position.x, this.game.transform.position.y, this.game.transform.position.z);
        this.menu.SetActive(false);
        Debug.Log(this.menu.transform.position.x + " : " + this.menu.transform.position.y);

       

    }
	
	// Update is called once per frame
	void Update () {
        pos = Camera.main.WorldToScreenPoint(this.game.transform.position);
        this.menu.transform.position = new Vector3(pos.x, pos.y, pos.z);

        if (StaticClasses.getTower(towerNum))
        {
            if (StaticClasses.getActive(this.towerNum) == false)
            {
                this.menu.SetActive(true);
            }
            else
            {
                this.isActiveView.SetActive(true);
                this.menu.SetActive(false);
            }
        }
        else
        {
            if (StaticClasses.getActive(this.towerNum)== false)
            {
                this.menu.SetActive(false);
            }else
            {
                this.isActiveView.SetActive(false);
                this.menu.SetActive(false);
            }
        }
	}

}
