using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Shoot : MonoBehaviour {
    public GameObject bullet;
    public int speed = 0;
    bool isShooting = false;
    public static float fireRate = 1f;
    int listSize = 0;
    public GameObject spawn;
    public int rotation = 0;
    public Slider health;
    public int offset = 0;
    public int BulletDam = StaticClasses.bulletPower;


    public int counter = 0;


    // life 

    List<GameObject> enemyList = new List<GameObject>();


    // Use this for initialization
    void Start()
    {

        StartCoroutine("shootTime");
        this.health = GameObject.FindGameObjectWithTag("Tower1").GetComponent<Slider>();
        this.health.gameObject.transform.position = new Vector3(this.spawn.transform.position.x, this.spawn.transform.position.y + offset, this.spawn.transform.position.z);
      // this.health.gameObject.transform.rotation = new Vector3(this.spawn.t, this.spawn.transform.position.y, this.spawn.transform.position.z);
    }

    public int getSpeed()
    {
        return this.speed;
    }

    // Update is called once per frame
    void Update()
    {   
        if(StaticClasses.powerActive && StaticButtonClickLogic.activeFrenzy)
        {
            int i;
            for(i = 0; i < enemyList.Count; i++)
            {
                if(this.enemyList[i].GetComponent<Enemy>() != null)
                {
                    this.enemyList[i].GetComponent<Enemy>().Life = 0;
                }
            }
            checkEnemy();
            StaticButtonClickLogic.activeFrenzy = false;
        }
        
        checkShoot();
        checkEnemy();
    }

    IEnumerator shootTime()
    {
        while (true)
        {
            yield return new WaitForSeconds(fireRate);
            if (this.isShooting == true)
            {
                shoot();
            }
        }
    }

    private void shoot()
    {
       //if (this.enemyList[0] != null){
            GameObject bull = GameObject.Instantiate(this.bullet);
            //ameObject bull = this.bullet;
            bull.GetComponent<Bullet>().bulletDamage *= StaticClasses.bulletPower;
            Debug.Log("---------------------------- bulletMulitipler: " + StaticClasses.bulletPower);
            Debug.Log("***************************** bullet damage: " + bull.GetComponent<Bullet>().bulletDamage);

                Transform g = this.spawn.transform;
            
                Debug.Log("Dead: " + this.enemyList[0].ToString());
                g.transform.LookAt(this.enemyList[0].transform.position);
            

            // this.spawn.transform.rotation = Quaternion.Euler(new Vector3(g.transform.rotation.x, g.transform.rotation.y + this.rotation, g.transform.rotation.z));
            //this.spawn.transform.position = new Vector3(g.transform.position.x, g.transform.position.y, g.transform.position.z);
            this.spawn.transform.rotation = g.transform.rotation;
            this.spawn.transform.position = g.transform.position;
            bull.transform.rotation = this.spawn.transform.rotation;
            bull.transform.position = this.spawn.transform.position;
            // bull.transform.rotation = this.transform.rotation;

            //GameObject bulls = GameObject.Instantiate(bull);
            bull.GetComponent<Rigidbody>().velocity += bull.transform.forward * this.speed;
            Destroy(bull, 2f);
      //  }
    }

    void OnTriggerEnter(Collider col)
    {
        Debug.Log("Trigger");
        if (col.gameObject.name.Contains("Enemy"))
        {
            this.enemyList.Add(col.gameObject);
            
            //this.listSize++;
            Debug.Log("Adding Enemy");
        }
    }

    void OnTriggerExit(Collider col)
    {
        if (col.gameObject.CompareTag("Enemy"))
        {

            if (this.enemyList.Contains(col.gameObject))
            {
                this.enemyList.Remove(col.gameObject);

                int towerNum = StaticClasses.getTowerNum(this.spawn.transform.position);
                StaticClasses.newTowerLife[towerNum] -= 10;

                //this.listSize--;
                Debug.Log("Removing Enemy");
            }
        }
    }

    void OnTriggerStay(Collider col)
    {
        if (this.enemyList.Count > 0 && col.gameObject.CompareTag("Enemy"))
        {
            if (!this.enemyList.Contains(col.gameObject) && col.gameObject.GetComponent<Enemy>().Life > 0)
            {
                this.enemyList.Add(col.gameObject);
                //this.listSize++;
                Debug.Log("readding element!");
            }
        }
    }

    bool checkEnemy()
    {
        Debug.Log(this.listSize);
        if (this.enemyList.Count > 0)
        {
            for (int i = 0; i < this.enemyList.Count; i++)
            {

                if (this.enemyList[i].GetComponent<Enemy>().isDead)
                {

                    GameObject f = this.enemyList[i];
                    this.enemyList.RemoveAt(i);
                    f.GetComponent<Enemy>().kill();
                   // Destroy(f);
                    Debug.Log("Deletion");
                   // Debug.Break();
                    //this.enemyList[i].GetComponent<Enemy>().isRemoved = true;
                    //this.enemyList[i].GetComponent<Enemy>().kill();

                    //listSize--;
                }
            }
            if (this.enemyList.Count > 0)
            {
                return true;
            }
        }
       
            return false;
        
    }

    void checkShoot()
    {
        if (this.enemyList.Count > this.counter)
        {
            this.isShooting = true;

        }
        else
        {
            this.isShooting = false;
            StopCoroutine("ShootTime");
        }
    }
}
