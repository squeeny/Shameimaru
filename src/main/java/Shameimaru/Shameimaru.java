package Shameimaru;

import Shameimaru.chr.chr_aya;
import Shameimaru.relics.AbstractAyaRelic;
import Shameimaru.relics.starter.blackbirdfeather;
import Shameimaru.relics.starter.blackbirdwings;
import Shameimaru.vars.aya_card_magic;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static Shameimaru.chr.chr_aya.characterStrings;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class Shameimaru implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber
{

    private static String modID = "shameimaru";
    public static String getModID() { return modID; }
    public static String makeID(String idText) { return getModID() + ":" + idText; }

    public static Color lbColour = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
    public static final String SHOULDER1 = getModID() + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = getModID() + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = getModID() + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = getModID() + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = getModID() + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = getModID() + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = getModID() + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = getModID() + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = getModID() + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = getModID() + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = getModID() + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = getModID() + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = getModID() + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = getModID() + "Resources/images/charSelect/charBG.png";

    public static final String mintyID = "mintyspire";
    public static final boolean hasMinty;
    static {
        hasMinty = Loader.isModLoaded(mintyID);
        if (hasMinty) { System.out.println("has minty, very cool"); }
    }

    public static SpireConfig ayaConfig;

    public Shameimaru() {
        BaseMod.subscribe(this);

        Properties ayaDefaults = new Properties();
        ayaDefaults.setProperty("Ayaya", "FALSE");
        try { ayaConfig = new SpireConfig("Shameimaru", "ayaMod", ayaDefaults);
        } catch (IOException e) { e.printStackTrace(); }

        BaseMod.addColor(chr_aya.Enums.AYA_COLOUR, lbColour, lbColour, lbColour,
                lbColour, lbColour, lbColour, lbColour,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) { return modID + "Resources/" + resourcePath; }
    public static String makeImagePath(String resourcePath) { return modID + "Resources/images/" + resourcePath; }
    public static String makeRelicPath(String resourcePath) { return modID + "Resources/images/relics/" + resourcePath; }
    public static String makeOutlinePath(String resourcePath) { return modID + "Resources/images/relics/outline/" + resourcePath; }
    public static String makePowerPath(String resourcePath) { return modID + "Resources/images/powers/" + resourcePath; }
    public static String makeCardPath(String resourcePath) { return modID + "Resources/images/cards/" + resourcePath; }
    public static String makeCharacterPath(String resourcePath) { return modID + "Resources/images/character/" + resourcePath; }
    public static String makeEffectPath(String resourcePath) { return modID + "Resources/images/effects/" + resourcePath; }
    public static void initialize() { Shameimaru shameimaru = new Shameimaru(); }
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new chr_aya(characterStrings.NAMES[0], chr_aya.Enums.AYA),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, chr_aya.Enums.AYA);
    }
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new blackbirdfeather(), chr_aya.Enums.AYA_COLOUR);
        BaseMod.addRelicToCustomPool(new blackbirdwings(), chr_aya.Enums.AYA_COLOUR);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new aya_card_magic());
        new AutoAdd(modID)
                .packageFilter("Shameimaru.cards")
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, getModID() + "Resources/localization/eng/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, getModID() + "Resources/localization/eng/Uistrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostInitialize() {
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID(Shameimaru.class.getSimpleName()));
        String[] TEXT = uiStrings.TEXT;
        ModPanel settingsPanel = new ModPanel();
        Texture badgeImg = new Texture("shameimaruResources/images/badge.png");
        BaseMod.registerModBadge(badgeImg, "${project.name}", "Squeeny", "${project.description}", settingsPanel);
        ModLabeledToggleButton Ayaya = new ModLabeledToggleButton(TEXT[0], 350.0f, 450.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, ayaConfig.getBool("Ayaya"), settingsPanel, label -> {}, button -> {
            ayaConfig.setBool("Ayaya", button.enabled);
            try {ayaConfig.save();} catch (IOException e) {e.printStackTrace();}
        });
        settingsPanel.addUIElement(Ayaya);
    }
}

