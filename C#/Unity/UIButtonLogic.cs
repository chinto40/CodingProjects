using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UIButtonLogic : MonoBehaviour {

    // Use this for initialization
     public Button saveButton;
     public Button InventoryButton;
     public Button Red;
     public Button Blue;

    int count = 0;
    //[SerializeField] private Button[] UIButtonList;
    //[SerializeField] private Button[] UIButtonList;


    // Following the order of the button logics
    // 1. Store
    // 2. Invenory


    void Start()
    {
        this.saveButton.onClick.AddListener(StoreOnAction);
        this.InventoryButton.onClick.AddListener(InventoryOnAction);
        this.Red.onClick.AddListener(RedOnAction);
        this.Blue.onClick.AddListener(BlueOnAction);
    }

    void RedOnAction()
    {
        // chaning to Blue
        StaticClasses.UIButtonChange = 3;
    }

    void BlueOnAction()
    {
        // changing to red
        StaticClasses.UIButtonChange = 2;
    }


    void StoreOnAction()
    {
        StaticClasses.UIButtonChange = 0;
        //count++;
        //Debug.Log("Store Click: " + this.count + "Button Number: " + StaticClasses.UIButtonChange);
      
        //SceneManager.LoadScene("SampleScene");
    }

    void InventoryOnAction()
    {
        StaticClasses.UIButtonChange = 1;
        //count++;
        //Debug.Log("Invenotry Click: " +this.count + "Button Number: " + StaticClasses.UIButtonChange);
    }
    // Update is called once per frame
    void Update () {
		
	}

    public Button getSaveButton()
    {
        return this.saveButton;
    }
}
