import UIKit
import shared

class ViewController: UIViewController, MembersView {
  
  let memberList = MemberList()
  
  lazy var presenter: MembersPresenter = {
    MembersPresenter(view: self, repository: AppDelegate.appDelegate.dataRepository, settings: AppDelegate.appDelegate.settings)
  }()
  
  var isUpdating = false

  @IBOutlet weak var greeting: UILabel!
  @IBOutlet weak var membersTableView: UITableView!
  @IBOutlet weak var organization: UITextField!
    
  override func viewWillAppear(_ animated: Bool) {
    presenter.onCreate()
  }
  
  override func viewWillDisappear(_ animated: Bool) {
    presenter.onDestroy()
  }
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view, typically from a nib.
    
    greeting.text =  Greeting().greeting()
    
    membersTableView.register(UINib(nibName: "MemberCellTableViewCell", bundle: nil), forCellReuseIdentifier: "MemberCell")
  }
  
    @IBAction func show(_ sender: UIButton) {
        if let organization = self.organization.text {
          self.organization.resignFirstResponder()
          presenter.update(organization: organization)
        }
    }

  func onUpdate(members: [Member], organization: String) {
    self.organization.text = organization
    self.memberList.updateMembers(newMembers: members)
    self.membersTableView.reloadData()
  }
}

extension ViewController: UITableViewDataSource {
  
  func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return self.memberList.members.count
  }
  
  func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    let cell = tableView.dequeueReusableCell(withIdentifier: "MemberCell", for: indexPath) as! MemberCellTableViewCell
    let member = self.memberList.members[indexPath.row]
    cell.memberName.text = member.login
    cell.memberAvatar.load(url: URL(string: member.avatar_url)!)
    return cell
  }
}

extension UIImageView {
  func load(url: URL) {
    DispatchQueue.global().async { [weak self] in
      if let data = try? Data(contentsOf: url) {
        if let image = UIImage(data: data) {
          DispatchQueue.main.async {
            self?.image = image
          }
        }
      }
    }
  }
}
