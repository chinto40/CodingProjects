using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StaticButtonClickLogic : MonoBehaviour {

    bool boom = false;
    bool[] isPower = new bool[8];
    int sec = 0;
    public static bool activeFrenzy = false;
    public static bool activeTowerBattle = false;

    int currentActive = 0;
	// Use this for initialization
	void Start () {
        for (int i = 0; i < this.isPower.Length; i++)
        {
            this.isPower[i] = false;
        }

    }
	
	// Update is called once per frame
	void Update ()
    {   
     if(StaticClasses.powerActive == true)
        {
            if(StaticClasses.sec >= StaticClasses.powerEndTime)
            {
                StaticClasses.powerActive = false;


                setCurrentPowerUpOff(this.currentActive);


            }
        }

 
	}

    public bool powerIsActive()
    {
        for (int i = 0; i < 8; i++)
            if (isPower[i] == true)
                return true;

        return false;
    }


    void setCurrentPowerUpOff(int powerUp)
    {
        switch (powerUp)
        {
            case 1: // Boom Shot
                StaticClasses.bulletPower = StaticClasses.iBullPowDefault;
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:

                break;
            default:
                break;

        }
    }


    public void onActionBoomShot()
    {
        // cost
        // 
        if (StaticClasses.Score < 100)
            return;

        if (StaticClasses.powerActive == true)
        {
            StaticClasses.Score -= 150;
            StaticClasses.powerEndTime += StaticClasses.dur;
            return;
        }
        this.currentActive = 1;
        StaticClasses.powerActive = true;
        StaticClasses.bulletPower = 1000;
        StaticClasses.powerEndTime = StaticClasses.sec + StaticClasses.dur;
 
    }

    public void OnActionCastelBlaster()
    {
        Debug.Log("Clicked Castle OnAction");
    }

    public void OnActionDarkEnergy()
    {
        Debug.Log("Clicked DarkEnergy OnAction");
    }

    public void OnActionDevineArmor()
    {
        Debug.Log("Clicked Devine OnAction");
    }
    public void OnActionDormantPower()
    {
        Debug.Log("Clicked Dormant OnAction");
    }
    public void OnActionFlashBang()
    {
        Debug.Log("Clicked FlashBang OnAction");
    }
    public void OnActionForgottenFruit()
    {
        Debug.Log("Clicked ForgottenFruit OnAction");
    }
    public void OnActionGoldenMail()
    {
        Debug.Log("Clicked GoldenMail OnAction");
    }
    public void OnActionInker()
    {
        Debug.Log("Clicked Inker OnAction");
    }
    public void OnActionInstaFrenzy()
    {
        if (StaticClasses.Score < 250 || StaticClasses.powerActive)
            return;
        activeFrenzy = true;
        StaticClasses.Score -= 250;
        StaticClasses.powerActive = true;
        StaticClasses.powerEndTime = StaticClasses.sec + 1;
        //StaticClasses.Frenzy = true;
        Debug.Log("Clicked InstaFrenzy OnAction");
    }
    public void OnActionInstaKill()
    {
        Debug.Log("Clicked InstaKill OnAction");
    }
    public void OnActionInstantGreaterLife()
    {
        Debug.Log("Clicked InstantGreaterLife OnAction");
    }
    public void OnActionLifeSteal()
    {
        Debug.Log("Clicked Life Steal OnAction");
    }
    public void OnActionLiquidHeart()
    {
        Debug.Log("Clicked Liquid Heart OnAction");
    }
    public void OnActionMagicBox()
    {
        Debug.Log("Clicked MagicBox OnAction");
    }
    public void OnActionParmaSteal()
    {
        Debug.Log("Clicked ParmaSteal OnAction");
    }
    public void OnActionReflexivePurple()
    {
        Debug.Log("Clicked ReflexivePurple OnAction");
    }
    public void OnActionRitualStone()
    {
        Debug.Log("Clicked Ritual Stone OnAction");
    }

    public void OnActionSlipperyDeath()
    {
        Debug.Log("Clicked SlipperyDeath OnAction");
    }
    public void OnActionSourApple()
    {
        Debug.Log("Clicked SourApple OnAction");
    }
    public void OnActionTowerBattle()
    {
        if (StaticClasses.Score < 100 || StaticClasses.powerActive)
            return;

        StaticClasses.Score -= 100;
        StaticClasses.powerEndTime += StaticClasses.dur;
        this.currentActive = 1;
        StaticClasses.powerActive = true;
        Shoot.fireRate = 0.5f;
        StaticClasses.powerEndTime = StaticClasses.sec + StaticClasses.dur;
        Debug.Log("Clicked TowerBattle OnAction");
    }
    public void OnActionTowerGreaterHealth()
    {
        Debug.Log("Clicked Tower Great Health OnAction");
    }
    public void OnActionTowerLesserHealth()
    {
        Debug.Log("Clicked Tower Lesser Health OnAction");
    }
    public void OnActionTowerUpgrade()
    {

        if (StaticClasses.Score < 200)
            return;

        StaticClasses.Score -= 200;
        Debug.Log("\t\t...newScore = " + StaticClasses.Score);

        StaticClasses.iBullPowDefault += 2;


        if (StaticClasses.powerActive == false)
            StaticClasses.bulletPower = StaticClasses.iBullPowDefault;

        Debug.Log("\t\t...new iBullPowDefault = " + StaticClasses.iBullPowDefault);

        int i;
        for (i = 0; i < 4; i++)
        {
            StaticClasses.newTowerLife[i] += 35;
            TowerHealth.towerHealth[i].maxValue += 35;
            TowerHealth.towerHealth[i].value = TowerHealth.towerHealth[i].maxValue;

        }

        Debug.Log("Clicked TowerUpgrade OnAction");
    }
    public void OnActionToxicPip()
    {
        Debug.Log("Clicked Toxic Pip OnAction");
    }
    public void OnActionTrinquit()
    {
        Debug.Log("Clicked Trinquit OnAction");
    }
}
