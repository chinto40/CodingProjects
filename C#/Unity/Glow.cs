using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Glow : MonoBehaviour {

    [SerializeField] private GameObject spawnPlatform;
    [SerializeField] private Light areaLight;
    [SerializeField] private float minLightCheck;
    [SerializeField] private float maxLightCheck = 1f;

    private Material spawnPlatMaterial;

    bool isGlowing = false;

    private Color NirvanaColorChange;
    private Color red;
    private float colorChange;
    private Color minAlphaRed;
    private int count = 0;


	// Use this for initialization
	void Start () {
        this.spawnPlatMaterial = this.spawnPlatform.gameObject.GetComponent<MeshRenderer>().material;
        this.red = new Color(255, 0, 0, 255);
        this.colorChange = this.spawnPlatMaterial.color.a;
        this.minAlphaRed = new Color(255, 0, 0, 80);

        //this.spawnPlatMaterial.color = this.minAlphaRed;
	}
	
	// Update is called once per frame
	void Update () {
        this.changeRedAlphaColor();
       
	}
    void changeLightIntensity()
    {
        if (this.isGlowing == false)
        {

        }
        else if (this.isGlowing == true)
        {

        }
    }

    void changeRedAlphaColor()
    {
        if (this.isGlowing == false){
            if(this.spawnPlatMaterial.color.a < 255f){
                this.colorChange += 1f;
                this.spawnPlatMaterial.color = new Color(255, 0, 0, this.colorChange);
              //  Debug.Log("Up: Alpha Color: " + this.spawnPlatMaterial.color.a);
            }else{
                this.isGlowing = true;
                
            }
            //Debug.Log(this.isGlowing+": "+ this.count);
        }
        else if (this.isGlowing == true){
            if (this.spawnPlatMaterial.color.a > 80f){
                this.colorChange -= 1f;
                this.spawnPlatMaterial.color = new Color(255, 0, 0, this.colorChange);
               // Debug.Log("Down: Alpha Color: " + this.spawnPlatMaterial.color.a);
            }else{
                this.isGlowing = false;
               // Debug.Log(this.isGlowing);
            }
            //Debug.Log(this.isGlowing + ": " + this.count);
        }
        //this.count++;
    }


}
