using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnEnemies : MonoBehaviour {

    // Use this for initialization
    int sec = 0;
    public GameObject Spawn;
    public GameObject[] Enemy = new GameObject[3];
	void Start () {
        StartCoroutine(Spawns());
	}
	
	// Update is called once per frame
	void Update () {
		if(this.sec >= 7)
        {
            GameObject f = this.Enemy[Random.Range(0, 3)];
            f.transform.rotation = this.Spawn.transform.rotation;
            f.transform.position = this.Spawn.transform.position;
            f.tag = "Enemy";
            GameObject.Instantiate(f);
            this.sec = 0;
            StaticClasses.numOfEnemies--;
        }
	}
    IEnumerator Spawns()
    {
        while (true)
        {
            yield return new WaitForSeconds(1);
            sec += 1;
        }
    }
 }
