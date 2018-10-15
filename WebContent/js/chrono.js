var secondes = 0;
var timer;
var text = "";
             
function IndiquerMinutes(min)
{
	secondes = min * 60;
}
         	
            function Chrono()
            {
                if (secondes > 0)
                {
                    var minutes = Math.floor(secondes/60);
                    var heures = Math.floor(minutes/60);
                    secondes -= minutes * 60;
                    if (heures > 0)
                    {
                        minutes -= heures * 60;
                        if (minutes > 0)
                        {
                            text = "Il reste " + heures + ' h ' + minutes + ' min ' + secondes + ' sec';
                        }
                        else
                        {  
                            text = "Il reste " + heures + ' h ' + secondes + ' sec';
                        }
                        minutes = minutes + (heures * 60);
                        secondes = secondes + (minutes * 60) - 1;
                         
                    }
                    else if (minutes > 0)
                    {
                        text = "Il reste " + minutes + ' min ' + secondes + ' sec';
                        secondes = secondes + (minutes * 60) - 1;
                    }
                    else
                    {
                        text = "Il reste " + secondes + ' sec';
                        secondes = secondes + (minutes * 60) - 1;
                    }
                }
                else
                {
                    clearInterval(timer);
                    text = "Le temps est écoulé";
                }
                document.getElementById('chrono').innerHTML = text;
            }
            
            function DemarrerChrono()
            {
                timer = setInterval('Chrono()', 1000);
                 
            }
             