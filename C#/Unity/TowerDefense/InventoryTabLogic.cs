using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class InventoryTabLogic : MonoBehaviour {

    [SerializeField] private GameObject[] UIScrollView = new GameObject[4];
    [SerializeField] private GameObject menuUI;

    [SerializeField] private GameObject[] viewPorts = new GameObject[4];
    
    //[SerializeField] private GameObject viewPort3;

    int maxViewPorts = 4;
    Image menuImage;

    Image[] viewImages = new Image[4];


    Color uiViewOne,uiViewTwo,uiViewThree, uiViewFour;

    // get it from the Scroll panel 


    //static variables 1 for store 2 for invnetory
    // Use this for initialization
    void Start () {

       // menuUI = GameObject.FindGameObjectWithTag("MenuStore");
        menuImage = menuUI.GetComponent<Image>();

       // this.viewPorts[0] = this.UIScrollView[0].GetComponent<Viewport>();

        // Colors. 
        this.viewImages[0] = viewPorts[0].GetComponentInChildren<Image>();
        this.viewImages[1] = viewPorts[1].GetComponentInChildren<Image>();
        this.viewImages[2] = viewPorts[2].GetComponentInChildren<Image>();
        this.viewImages[3] = viewPorts[3].GetComponentInChildren<Image>();
        //this.viewImages[2] = UIScrollView[2].GetComponentInChildren<Image>();

        uiViewOne = this.viewImages[0].color;
        uiViewTwo = this.viewImages[1].color;
        uiViewThree = this.viewImages[2].color;
        uiViewFour = this.viewImages[3].color;
        //uiViewThree = this.viewImages[2].color;

    }
	
	// Update is called once per frame
	void Update () {
        switch (StaticClasses.UIButtonChange)
        {
            case 0: // store button
                this.UIScrollView[0].SetActive(true);
                // this.menuUI.GetComponent<Image>()
                menuImage.color = this.uiViewOne; // change the color here to whatever it is...
                Debug.Log("Changing color to: 0" + this.uiViewOne);

                break;
            case 1: // invenotry button. 
                this.UIScrollView[1].SetActive(true);
                menuImage.color = this.uiViewTwo;
                Debug.Log("Changing color to: 1" + this.uiViewTwo);
                break;

            case 2:
                this.UIScrollView[2].SetActive(true);
                menuImage.color = this.uiViewThree;
                Debug.Log("Changing color to 2: " + this.uiViewThree);

                break;

            case 3:
                this.UIScrollView[3].SetActive(true);
                menuImage.color = this.uiViewFour;
                Debug.Log("Changing Color to 3: " + this.uiViewFour);
                break;
        }
        disable();
	}

    public void disable()
    {
      // disable everything but the selected one
      
        // iterate through it. 
        for(int index = 0; index <  this.UIScrollView.Length; index++)
        {
            if(index != (StaticClasses.UIButtonChange))
            {
                this.UIScrollView[index].SetActive(false);

            }
        }


    }
}
