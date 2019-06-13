using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SideBarCheck : MonoBehaviour {

    int check;
    public GameObject sideBar;
    public GameObject topBar;

    public Button swap;
    public Sprite arrowUp;
    public Sprite arrowDown;


	// Use this for initialization
	void Start () {
        check = StaticClasses.menuCheck;
        this.swap.onClick.AddListener(swapOnAction);
	}
	
	// Update is called once per frame
	void Update () {
        if (check != StaticClasses.menuCheck)
        {
            rotateMenu();
            check = StaticClasses.menuCheck;
        }

        if (StaticClasses.ismenu == true)
        {
            setActive(2);
        }else
        {
            setActive(1);
        }



    }

    void setActive(int type)
    {
        if(type == 1)
        {
            // deactivate
            topBar.SetActive(false);
            sideBar.SetActive(false);
            swap.gameObject.SetActive(false);
        }
        else
        {
            // activate
            rotateMenu();
            swap.gameObject.SetActive(true);
        }
    }

    void swapOnAction()
    {
        if(StaticClasses.menuCheck == 0)
        {
            StaticClasses.menuCheck++;
        }
        else
        {
            StaticClasses.menuCheck--;
        }
    }

    void rotateMenu()
    {
        switch (StaticClasses.menuCheck)
        {
            case 0: // side
                this.sideBar.SetActive(true);
                this.topBar.SetActive(false);
                buttonImageChange(1);
                break;
            case 1: // top
                this.sideBar.SetActive(false);
                this.topBar.SetActive(true);
                buttonImageChange(2);

                break;
        }
        
       

    }
    void buttonImageChange(int type)
    {
        switch (type)
        {
            case 1:
                this.swap.image.sprite = this.arrowUp;
                break;
            case 2:
                this.swap.image.sprite = this.arrowDown;
                break;
        }
    }
}
