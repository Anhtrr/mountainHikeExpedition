<h1> Mountain Hike Expedition </h1>

<h2> Instructions: </h2>
mountainHikeExpedition takes a single command line input that is the path to a .txt file. Within this .txt file, each line represents a 
single point on the Mountain (node of type RestStop in BST). At each point on the Mountain, the hiker will encounter either **SUPPLIES, 
OBSTACLES,** both, or neither. If there is an **OBSTACLE**, the hiker will need a complementing **SUPPLY** that will allow the hiker to pass that 
point of the mountain. mountainHikeExpedition's job is to find all possible paths the hiker can take to get to the bottom of the 
mountain.<br><br>

Each line should follow the following format: **"LABELS SUPPLIES OBSTACLES"** <br>
Please check sample input file: **input.txt** <br><br>

Valid **SUPPLIES** are: **food, raft, axe, or empty** <br>
Valid **OBSTACLES** are: **fallen tree, river, or empty** <br>

<h2> Running the Program: </h2>
**To compile the program:**  javac ./mountainHikeExpedition/BSTMountain.java ./mountainHikeExpedition/Hiker.java 
./mountainHikeExpedition/RestStop.java ./mountainHikeExpedition/MountainHike.java <br>
**To run the program with sample input:** java mountainHikeExpedition.MountainHike **path-to-mountainHikeExpedition/input.txt**
