using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class OnClick : MonoBehaviour {

    [SerializeField] private GameObject square;
    [SerializeField] private int y = 0;
    [SerializeField] private int x = 0;
    [SerializeField] private int TowerNum = 0;




    // Use this for initialization
    void Start () {
            
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKeyDown(KeyCode.Mouse0))
        {
            //Debug.Log("Mouse Click");
            //Function Click for certain area;
            InArea();

        }
	}

    bool InArea()
    {

        Vector3 mp = new Vector3(Input.mousePosition.x, Input.mousePosition.y, Camera.main.nearClipPlane);


        Vector3 sq = Camera.main.WorldToScreenPoint(this.square.transform.position);
        Vector3 sq0 = new Vector3 (sq.x - x , sq.y - y , sq.z );
        Vector3 sq2 = new Vector3(sq.x + x, sq.y + y, sq.z);
        int Hieght = (int)(sq0.y - sq2.y);
        int width = (int)(sq0.x - sq2.y);
        //Debug.Log("");
        //Debug.Log("");
        //Debug.Log("Mouse position is x: " + mp.x + " Y: " + mp.y);
        //Debug.Log("Square position is x: " + sq.x + " Y: " + sq.y);
        //Debug.Log("Square position is x: " + sq2.x + " Y: " + sq2.y);
        //Debug.Log("");
        //float angle = Mathf.Atan2(sq.y - mp.y, mp.x - sq.x) * Mathf.Rad2Deg;

        if ((mp.x > sq0.x && mp.x < sq2.x) && (mp.y > sq0.y && mp.y < sq2.y))
        {
           // Debug.Log("in the Area");
            StaticClasses.toggleTower(this.TowerNum);

            //Debug.Log("Instantiating..."+StaticClasses.isActive1);

        }




        return false;
    }
}
