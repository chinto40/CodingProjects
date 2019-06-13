using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TowerLevel : MonoBehaviour {

    public Text t;
    public Text text;

    public int x = 0, y = 0, z = 0;

	// Use this for initialization
	void Start () {

        this.text =  GameObject.Instantiate(t);
        this.text.text = "Level: 1";
	}
	
	// Update is called once per frame
	void Update () {
        Vector3 sq = Camera.main.WorldToScreenPoint(this.transform.position);
        this.text.transform.position = new Vector3(sq.x +x, sq.y+y, sq.z+z);
        this.text.text = "Level: " + gameObject.GetComponent<Archer>().getLevel();
	}
}
