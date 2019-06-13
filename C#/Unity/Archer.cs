using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Archer : MonoBehaviour {

    public int level = 1;



	// Use this for initialization
	void Start () {
		
	}
	
    public void upgrade()
    {
        if(this.level < 3)
        {
            //changes the particles. 
            this.level++;
        }
    } 

    public int getLevel()
    {
        return level;
    }

	// Update is called once per frame
	void Update () {
		
	}
}
