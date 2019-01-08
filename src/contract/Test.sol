contract Test{
    
    
    
    string name;
    uint balance;

    function Test(string n,uint b){
        
        name="abc";
        balance=10;

        name=n;
        balance=b;
    }
    function get()constant returns(string,uint){

        return (name,balance);
    }
    



}