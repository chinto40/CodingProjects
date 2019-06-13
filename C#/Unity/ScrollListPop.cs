using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

/*
public class Item
{
    public Text name;
    public Image spriteItem;
    public int cost;
}*/


public class ScrollListPop : MonoBehaviour {

    [SerializeField] private GameObject[] pop = new GameObject[2];
    //public Item item;
    public Text Iname;
    public Image Isprite;
    public int Icost;


    // Here we add to the List. 
	// Use this for initialization
	void Start () {

        //populate the price
        for(int j = 0; j < this.pop.Length; j++)
        {
            GameObject i = (GameObject)Instantiate(pop[j], this.transform);
        }
       
        //GameObject f = (GameObject)Instantiate(pop[1], this.transform);
    }
	
	// Update is called once per frame
	void Update () {
		
	}


    void Add()
    {


        // instantiate object in the transform.
    }
}
