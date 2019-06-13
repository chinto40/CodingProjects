using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GuiTextBox : MonoBehaviour {

    Button b;
    public int cost = 0;
    GUI l;
	// Use this for initialization
	void Start () {
        b = this.GetComponent<Button>();
	}

    private void OnMouseOver()
    {
       GUI.Label(new Rect(10, 40, 100, 40), "Cost is: " + cost);
    }

    private void OnMouseExit()
    {
       
    }

    // Update is called once per frame
    void Update () {
		
	}
}
