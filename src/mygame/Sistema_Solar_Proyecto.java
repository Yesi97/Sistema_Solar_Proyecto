package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 * This is the Sistema_Solar_Proyecto Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */


public class Sistema_Solar_Proyecto extends SimpleApplication 
{

    public static void main(String[] args) 
    {
        Sistema_Solar_Proyecto app = new Sistema_Solar_Proyecto();
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
        /**
         * Los objetos Node podrías imaginarlos como cajas que contendrán otras cajas o modelos (esferas, 
         * personaje, etc.). Al igual que si mueves una caja, se mueve todo lo de su interior, si rotas 
         * o trasladas un Node, aplica lo mismo a los objetos que contiene. 
         **/
        
        
        
        Node pivotMercurio = new Node("pivote_mercurio");
        Node pivotVenus = new Node("pivote_venus");
        Node pivotTierra = new Node("pivote_tierra");
        
        Node nodeSol = new Node("nodo_sol");
        Node nodeMercurio = new Node("nodo_mercurio");
        Node nodeVenus = new Node("nodo_venus");
        Node nodeTierra = new Node("nodo_tierra");
        
        
        
        /**
         * Para poder visualizar un objeto en jMonkey se necesita definir un objeto Geometry, 
         * el cual requiere los vértices y aristas del objeto tridimensional, así como el 
         * material de este.
         */
        
        /*Esfera para el sol*/
        
        Sphere sol = new Sphere(30, 30, 1f);
        Geometry geomSol = new Geometry("Sol", sol);
        
        /**
         * Ya que utilizaremos una figura geométrica básica, la “esfera”, el motor proporción 
         * una clase la cual sólo debemos revisar en el API, para identificar cada parámetro de 
         * su constructor. En este caso, para la clase Sphere(int zSample, int radialSamples, 
         * float radius), zSample – El número de muestras a lo largo del eje z, 
         * radialSample -  El número de muestras a lo largo del radial, radius – El radio de la esfera.
         * 
         **/
        
        /* Creamos 4 esferas: mercurio, venus, tierra y la luna*/
        
        Sphere mercurio = new Sphere(30, 30, 0.5f);
        Geometry geomMercurio = new Geometry("Mercurio", mercurio);
        
        Sphere venus = new Sphere(30, 30, 0.7f);
        Geometry geomVenus = new Geometry("Venus", venus);
        
        Sphere tierra = new Sphere(30, 30, 0.7f);
        Geometry geomTierra = new Geometry("Tierra", tierra);
        
        Sphere luna = new Sphere(30, 30, 0.3f);
        Geometry geomLuna = new Geometry("Luna", luna);
        
         /**
         * Por el momento utilizaremos materiales que en si son simples colores.  
         * Mas adelante tendrán otra apariencia.
         **/
         
         /*Materiales para cada una de nuestras esferas (sol, planetas y la luna)*/
         
        Material matSol = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matSol.setColor("Color", ColorRGBA.Yellow);
        geomSol.setMaterial(matSol);
        
        Material matMercurio = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matMercurio.setColor("Color", ColorRGBA.Brown);
        geomMercurio.setMaterial(matMercurio);
        
        Material matVenus = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matVenus.setColor("Color", ColorRGBA.Orange);
        geomVenus.setMaterial(matVenus);
        
        Material matTierra = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matTierra.setColor("Color", ColorRGBA.Blue);        
        geomTierra.setMaterial(matTierra);
        
        Material matLuna = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matLuna.setColor("Color", ColorRGBA.White);
        geomLuna.setMaterial(matLuna);
        
        
        geomLuna.setLocalTranslation(1f, 0f, 0f);
        
        
         /**
         * Los Nodes son un medio para agrupar otros nodos y/o objetos del tipo Geometry. Además, 
         * se utilizan comúnmente para aplicar transformaciones a los spatials que agrupan.
         * 
         * A través del comando “attachChild( Geometry)” estaremos organizando lo que cada 
         * Node tendrá, esto lo hacemos de acuerdo a una idea que definimos previamente, la 
         * cual nos permitirá generar la ilusión de rotación en forma de orbita para cada uno de 
         * nuestros planetas
         */
         
        nodeTierra.attachChild(geomTierra);
        nodeTierra.attachChild(geomLuna);
        
        nodeSol.attachChild(geomSol);
        
        nodeMercurio.attachChild(geomMercurio);
        nodeVenus.attachChild(geomVenus);
        
        nodeMercurio.setLocalTranslation(2f, 0, 0f);
        nodeVenus.setLocalTranslation(4f, 0, 0f);
        nodeTierra.setLocalTranslation(6f, 0, 0f);
        
        /*Estos pivotes serán de ayuda para asignar la velocidad que tendrá cada uno*/
        
        pivotTierra.attachChild(nodeSol);
        pivotMercurio.attachChild(nodeMercurio);
        pivotVenus.attachChild(nodeVenus);
        pivotTierra.attachChild(nodeTierra);
        
        
        /**
         * Recuerda, para que se pueda visualizar algún Spatial(Node o Geometry) en el escenario, 
         * necesita estar adjuntado al “rootNode”. Además, ya que se genera un relación de padre – hijo, 
         * si agregas al padre por lo tanto también agregas el hijo, regresando a la visualización de cajas, 
         * si agregas una caja, por transitividad, también lo que este adentro estará agregado. 
        **/
        
        rootNode.attachChild(pivotMercurio);
        rootNode.attachChild(pivotVenus);
        rootNode.attachChild(pivotTierra);
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
        //TODO: add update code
        
        /**
         * Time per frames = tpf
         * tpf Indica el tiempo que toma al motor genera un frame(imagen renderizada en la pantalla) 
         * antes de entrar nuevamente al metodo simpleUpdate para generar un nuevo frame.
         * Esto indica que si utilizamos esta variable para determinar la cantidad de movimiento en 
         * cada frame, este movimiento será más o menos rápido, dependiendo de la máquina que se utilice, 
         * pero por el momento podremos utilizar esta variable para actualizar la rotación de cada objeto 
         * en cada ciclo de la función simpleUpdate
        **/
        
        rootNode.getChild("nodo_tierra").rotate(0,tpf, 0);
        // tpf indica la velocidad que tendrá cada uno
        
        //Velocidad del planeta Mercurio alrededor del Sol
        rootNode.getChild("pivote_mercurio").rotate(0,tpf/5, 0);
        
        //Velocidad del planeta Venus alrededor del Sol
        rootNode.getChild("pivote_venus").rotate(0,tpf/9, 0);
        
        //Velocidad del planeta Tierra alrededor del Sol
        rootNode.getChild("pivote_tierra").rotate(0, tpf/2, 0);
    }

    @Override
    public void simpleRender(RenderManager rm) 
    {
        //TODO: add render code
    }
}
