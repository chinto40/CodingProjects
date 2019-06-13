using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class Enemy : MonoBehaviour {

    public float speed = 10f;

    public int Life = 100;

    GameObject player;
    Vector3 vec = Vector3.zero;
    bool start = false;
    NavMeshAgent nav;
    public bool isDead = false;
    public GameObject endGame;
    float elapsed = 0.0f;
    Vector3 lastPath = Vector3.zero;
   public bool isRemoved = false;
   
    // Use this for initialization
    void Start()
    {
        this.endGame = GameObject.FindGameObjectWithTag("END");
        this.nav = gameObject.GetComponent<NavMeshAgent>();
        //StartCoroutine("checkDis");
    }

    // Update is called once per frame
    void Update()
    {
        //float dis = Vector3.Distance(GameObject.FindGameObjectWithTag("Player").GetComponent<Transform>().position, this.transform.position);
        
        this.nav.SetDestination(this.endGame.transform.position);

        
        if (this.Life <= 0)
        {
            this.isDead = true;
           // kill();
            /* if (isRemoved == true)
            {
                kill();
            }*/
            
        }
        // lastPath = new Vector3(this.transform.position.x, this.transform.position.y, this.transform.position.z);

    }



    public void kill()
    {
        StaticClasses.numOfEnemies--;
        Destroy(this.gameObject);
        //Debug.Break();
    }
    private void OnCollisionEnter(Collision col)
    {
        if (col.gameObject.CompareTag("END"))
        {
            // player loses life
           // StaticClasses.playerLife -= 20;
            Destroy(this.gameObject);

        }
        if (col.gameObject.CompareTag("Bullet"))
        {
            // Debug.Log("Dmage enemy");

            
            //this.Life -= col.gameObject.GetComponent<B>
            //this.Life -= (col.gameObject.GetComponent<Bullet>().Damage);
            this.Life -= col.gameObject.GetComponent<Bullet>().bulletDamage;
            Destroy(col.gameObject);
            StaticClasses.Score += 10;

            Debug.Log("_______________________________ enemyHealth" + this.Life);

            //Debug.Log("life: " + this.Life);

            // this.anim.SetInteger("anim", 0);
        }
    }

    IEnumerator checkDis()
    {
        while (true)
        {
            yield return new WaitForSeconds(2f);
            if (Vector3.Distance(lastPath, this.transform.position) == 0.0f)
            {
               // NavMesh.CalculatePath(transform.position, this.endGame.transform.position, NavMesh.AllAreas, this.nav.path);

            }
        }
    }


    public int getLife()
    {
        return this.Life;
    }

    void setLife(int nlife)
    {
        this.Life = nlife;
    }
}
