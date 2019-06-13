using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class HealthBar : MonoBehaviour {

    public Slider healthBar;
    public  Text name;
    //public GameObject enemy;
    Vector3 pos;
    public Vector3 adds;
    // Use this for initialization
    public Slider s;
    public Text t;
	void Start () {
        //this.name = gameObject.GetComponent<Text>();
        this.s = GameObject.Instantiate(this.healthBar);
        this.t = GameObject.Instantiate(this.name);
        this.pos =  Camera.main.WorldToScreenPoint(this.transform.position);
        this.healthBar.value = this.GetComponent<Enemy>().Life;
    }
	
	// Update is called once per frame
	void Update () {
        this.pos = Camera.main.WorldToScreenPoint(this.transform.position);
        this.s.value = this.GetComponent<Enemy>().getLife();
        this.t.text = "Life: " + this.GetComponent<Enemy>().getLife();
        this.s.transform.position = new Vector3( this.pos.x+adds.x,this.pos.y+adds.y, this.pos.z+adds.z);
        this.t.transform.position = new Vector3(this.pos.x + this.adds.x, this.pos.y + this.adds.y, this.pos.z + adds.z);

    }
}
