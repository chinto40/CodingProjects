using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;



public class StaticClasses : MonoBehaviour {


    public static int numOfEnemies = 20;
    public static int maxSoliders = 3;
    public static int solider = 0;

    //public static int playerLife = 100;
    public static int sec = 0;



    public static bool isMenuTower1 = false;
    public static bool isActive1 = false;

    public static bool isMenuTower2 = false;
    public static bool isActive2 = false;

    public static bool isMenuTower3 = false;
    public static bool isActive3 = false;

    public static bool isMenuTower4 = false;
    public static bool isActive4 = false;



    public static int UIButtonChange = 0;


    public static int menuCheck = 0;
    public static bool ismenu = false;


    public static GameObject tower1;


    public GameObject spawnPoint1;

    public static int[] newTowerLife = new int[4] { 100, 100, 100, 100 };
    public static GameObject[] towers = new GameObject[4];
    public static GameObject[] spawnpoints = new GameObject[4];

    public static bool powerActive = false;

    public static int iBullPowDefault = 1;
    public static int bulletPower = 1;

    public static int powerEndTime;
    public static int dur = 20;

    public static int Score = 1000;

   // public static bool Frenzy = false;

    // Use this for initialization
    void Start()
    {
        StartCoroutine("Time");
        spawnpoints[0] = GameObject.FindGameObjectWithTag("spawn0");
        spawnpoints[1] = GameObject.FindGameObjectWithTag("spawn1");
        spawnpoints[2] = GameObject.FindGameObjectWithTag("spawn2");
        spawnpoints[3] = GameObject.FindGameObjectWithTag("spawn3");
    }

    // Update is called once per frame
    void Update()
    {
        //Debug.Log(sec);
        if((isMenuTower1 == true /* && isActive1 == false*/) || (isMenuTower2 == true /*&& isActive2 == false )*/|| (isMenuTower3 == true /*&& isActive3 == false*/) || (isMenuTower4 == true /*&& isActive4 == false*/))){
            ismenu = true;
        }
        else{
            ismenu = false;
        }

    }


    public static bool getTower(int towerNum)
    {
        switch (towerNum)
        {
            case 1:
                return isMenuTower1;
            case 2:
                return isMenuTower2;
            case 3:
                return isMenuTower3;
            case 4:
                return isMenuTower4;
        }
        return false;
    }

    public static bool getActive(int towerNum)
    {
        switch (towerNum)
        {
            case 1:
                return isActive1;
            case 2:
                return isActive2;
            case 3:
                return isActive3;
            case 4:
                return isActive4;
        }
        return false;
    }

    public static void toggleTower(int towerNum)
    {
        switch (towerNum)
        {
            case 1:
                isMenuTower1 = toogle(isMenuTower1);
                //isActive1 = toogle(isActive1);
                break;
            case 2:
                isMenuTower2 = toogle(isMenuTower2);
                //isActive2 = toogle(isActive2);
                break;
            case 3:
                 isMenuTower3 = toogle(isMenuTower3);
                //isActive3 = toogle(isActive3);
                break;
            case 4:
                isMenuTower4 = toogle(isMenuTower4);
                //isActive4 = toogle(isActive4);
                break;

        }
        
    }
    public static void toggleActive(int towerNum)
    {
        switch (towerNum)
        {
            case 1:
               // isMenuTower1 = toogle(isMenuTower1);
                isActive1 = toogle(isActive1);
                break;
            case 2:
                //isMenuTower2 = toogle(isMenuTower2);
                isActive2 = toogle(isActive2);
                break;
            case 3:
                //isMenuTower3 = toogle(isMenuTower3);
                isActive3 = toogle(isActive3);
                break;
            case 4:
                //isMenuTower4 = toogle(isMenuTower4);
                isActive4 = toogle(isActive4);
                break;

        }

    }

    public static void addPoints()
    {
        Score += 100;
    }

    private static bool toogle(bool tog)
    {
        if(tog == true)
        {

            return false;
        }
        else
        {
 
            return true;
            
        }
    }

    IEnumerator Time()
    {
        while (true)
        {
            yield return new WaitForSeconds(1);
            sec += 1;
        }
    }

    public static int getTowerNum(Vector3 obj)
    {
        int i;
        for (i = 0; i < 4; i++)
        {
            //Debug.Log("STATIC:::::::: i=" + i);
            if (StaticClasses.spawnpoints[i].transform.position.x == obj.x)
            {
                //Debug.Break();

                Debug.Log("STATIC:FINAL------------- final i = " + i);

                return i;
            }
        }
        return -1;
    }

}
