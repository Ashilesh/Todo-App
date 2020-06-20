import React from 'react';
import './App.css';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      newItem: "",
      list: [],
      temp: null
    };
    this.getList();
  }

  async getList() {
    const response = await fetch("http://localhost:8080/Todo/1", {
      method: "GET",
      headers: { 'Content-Type': 'application/json' }
    });


    const list = await response.json();

    const array = [];

    for (const obj in list) {
      array.push({
        id: list[obj]["id"],
        title: list[obj]["title"],
        isComplete: list[obj]["complete"]
      });
    }

    console.log(array);

    this.setState({
      list : array
    });
    
  }

  async addItem(todoValue) {


    if (todoValue !== "") {

      // append all items in list
      // const list = [...this.state.list]

      // list.push(newItem);

      // this.setState({
      //   list: list,
      //   newItem: ""
      // });

      const send = {
        title: todoValue
      }

      const response = await fetch("http://localhost:8080/Todo/1", {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(send)
      });

      // if created then add into list
      if (response["status"] === 201) {
        const data = await response.json();
        this.setState({
          temp: data
        });

        console.log(data);

        const tempList = this.state.list;
        (await tempList).push(this.state.temp);

        this.setState({
          list: tempList,
          temp: null,
          newItem: ""
        });

        
      }

    }
  }

  deleteItem(id) {
    const list = [...this.state.list];
    const updatedList = list.filter(item => item.id !== id);
    this.setState({
      list: updatedList
    })
  }

  updateInput(input) {
    this.setState({
      newItem: input
    })

  }

  render() {
    return (
      <div>
        <h1 className="app-title"> Todo App</h1>
        <div className="container">
          Add an item...
          <br />
          <input
            type="text"
            className="input-text"
            placeholder="Write todo.."
            required
            // value is ""
            value={this.state.newItem}
            onChange={e => this.updateInput(e.target.value)}
          />
          <button
            className="add-btn"
            onClick={() => this.addItem(this.state.newItem)}
            disabled={!this.state.newItem.length}
          >Add Todo</button>
          <br />
          <div className="list">
            <ul>
              
              {this.state.list.map(item => {
                return (
                  <li key={item.id}>
                    <input
                      type="checkbox"
                      name="idDone"
                      checked={item.isComplete}
                      onChange={() => { }}
                    />
                    {item.title}
                    <button
                      className="btn"
                      onClick={() => this.deleteItem(item.id)}
                    >Delete</button>
                  </li>
                );
              })}  
              <li>
                <input type="checkbox" />
                  Todo 1
                  <button className="btn">Delete</button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    )
  }
}

export default App;