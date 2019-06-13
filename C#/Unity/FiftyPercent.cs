using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class FiftyPercent : MonoBehaviour
{
    public int playerStartHP = 100;
    public float playerCurrentHP;
    public Slider HPSlider;
    // Use this for initialization
    void Start()
    {

    }

    private void Awake()
    {
        playerCurrentHP = playerStartHP;
    }
    // Update is called once per frame
    void Update()
    {
        if (playerCurrentHP <= 0)
        {
            SceneManager.LoadScene("Lose");
        }
        HPSlider.value = playerCurrentHP;
    }

    private void OnTriggerEnter(Collider other)
    {
        playerCurrentHP = HPSlider.value;
        if (other.gameObject.tag.Equals("Enemy"))
        {
            playerCurrentHP -= 10;
            HPSlider.value = playerCurrentHP;
        }
    }

}